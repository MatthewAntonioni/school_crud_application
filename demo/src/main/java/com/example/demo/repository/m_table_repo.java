package com.example.demo.repository;

import com.example.demo.model.m_table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface m_table_repo extends JpaRepository<m_table, Integer>{
 
    @Query("select m.ID, m.Name, m.Date_joined, m.Status from m_table m") //the from needs to be the name of the model class not the database table
    List<Object[]> findAllNames();

    @Modifying //this tells spring that this method is a modify query
    @Transactional //this tells spring that this method is a transactional query
    @Query("update m_table m set m.Status = 'revoked' where m.ID = :id")
    int revokeMember(@Param("id") int id);

}


