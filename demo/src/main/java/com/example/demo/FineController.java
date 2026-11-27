package com.example.demo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// fines for one member
// get the/api/fines/{memberId} to pay their unpaid fines
// get the /api/fines/{memberId}/pay to pay all of them
@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final JdbcTemplate jdbc;

    public FineController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // unpaid fines for this member, with the book they were late on
    // a fine is linked to a loan, and the loan says which member and book
    @GetMapping("/{memberId}")
    public List<Map<String, Object>> unpaid(@PathVariable String memberId) {
        return jdbc.queryForList(
                "select f.ID as id, b.Title as title, f.Days_overdue as daysOverdue, "
                        + "f.Amount as amount, date_format(f.Date_issued, '%Y-%m-%d') as dateIssued "
                        + "from fine f "
                        + "join loan l on l.ID = f.L_ID "
                        + "join book b on b.ID = l.B_ID "
                        + "where l.M_ID = ? and f.Paid = false "
                        + "order by f.Date_issued",
                memberId);
    }

    // pay everything a member owes
    @PostMapping("/{memberId}/pay")
    public ResponseEntity<?> payAll(@PathVariable String memberId) {
        // how much they owe before paying
        BigDecimal total = jdbc.queryForObject(
                "select coalesce(sum(f.Amount), 0) from fine f "
                        + "join loan l on l.ID = f.L_ID "
                        + "where l.M_ID = ? and f.Paid = false",
                BigDecimal.class, memberId);

        if (total == null || total.signum() == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "You don't owe anything"));
        }

        // mark all their unpaid fines as paid
        jdbc.update("update fine f join loan l on l.ID = f.L_ID "
                        + "set f.Paid = true "
                        + "where l.M_ID = ? and f.Paid = false",
                memberId);

        return ResponseEntity.ok(Map.of("message", "Paid $" + total + ". Thanks!"));
    }
}