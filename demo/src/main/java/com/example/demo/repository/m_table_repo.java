package com.example.demo.repository;

import com.example.demo.model.m_table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface m_table_repo extends JpaRepository<m_table, Integer>{
 
    @Query("select m.ID, m.Name, m.Date_joined, m.Status from m_table m") //the from needs to be the name of the model class not the database table
    List<Object[]> findAllNames();

}
