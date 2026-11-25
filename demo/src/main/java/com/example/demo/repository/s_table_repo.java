package com.example.demo.repository;

import com.example.demo.model.s_table; //importing this class connects us to the table in the database

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface s_table_repo extends JpaRepository<s_table, Integer> {
    
//this method needs the name of the class your imported earlier and the data type of the primary key

//why we import jpa repo is because it gives access to a great many things we'd want to do in the database already

@Query("select s.name,s.email,s.position from s_table s")
List<Object[]> findAllNames();


}
