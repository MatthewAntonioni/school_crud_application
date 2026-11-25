package com.example.demo.controller;

import com.example.demo.repository.m_table_repo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController 
@RequestMapping("/api/M_table")
public class m_table_controller {
    
private final m_table_repo repo;

public m_table_controller(m_table_repo repo){

this.repo = repo;

}

@GetMapping 
public List<Object[]> getMember(){

return repo.findAllNames();

}

}
