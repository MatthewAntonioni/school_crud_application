package com.example.demo.repository;

import com.example.demo.model.s_table; //importing this class connects us to the table in the database

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface s_table_repo_S extends JpaRepository<s_table, Integer> {
    
    @Query("select s.ID, s.Name, s.Email, s.Position, s.Date_hired from s_table s")
    List<Object[]> findAllNames();

}
