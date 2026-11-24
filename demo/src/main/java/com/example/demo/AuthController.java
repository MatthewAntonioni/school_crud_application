package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Login and register.
// Check if all fields are filled in and add new row for member

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JdbcTemplate jdbc;

    public AuthController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public record MemberLogin(String id, String password) {
    }

    public record StaffLogin(String email, String password) {
    }

    public record Register(String name, String password) {
    }

    // member login from db
    // member login from db
    @PostMapping("/member/login")
    public ResponseEntity<?> memberLogin(@RequestBody MemberLogin in) {
        List<Map<String, Object>> rows = jdbc.queryForList(
                "select lpad(ID, 7, '0') as id, Name as name from member "
                        + "where ID = ? and Password = ? and Status = 'active'",
                in.id(), in.password());
        if (rows.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Wrong member ID or password"));
        }
        return ResponseEntity.ok(Map.of("role", "member",
                "id", rows.get(0).get("id"), "name", rows.get(0).get("name")));
    }

    //login using staff db
    @PostMapping("/staff/login")
    public ResponseEntity<?> staffLogin(@RequestBody StaffLogin in) {
        List<Map<String, Object>> rows = jdbc.queryForList(
                "select ID as id, Name as name, Position as position from staff "
                        + "where Email = ? and Password = ?",
                in.email(), in.password());
        if (rows.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Wrong email or password"));
        }
        Map<String, Object> s = rows.get(0);
        return ResponseEntity.ok(Map.of("role", "staff",
                "id", s.get("id"), "name", s.get("name"), "position", s.get("position")));
    }

    //reg new member, they get added to the database and get their id
    @PostMapping("/member/register")
    public ResponseEntity<?> register(@RequestBody Register in) {
        if (in.name() == null || in.name().isBlank() || in.password() == null || in.password().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Enter your name and a password"));
        }
        jdbc.update("insert into member (Name, Password) values (?, ?)",
                in.name().trim(), in.password());
        // the new member's ID = the biggest ID (the row we just added)
        String id = jdbc.queryForObject("select lpad(max(ID), 7, '0') from member", String.class);
        return ResponseEntity.ok(Map.of("id", id, "name", in.name().trim()));
    }
}
