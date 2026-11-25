package com.example.demo.controller;

import com.example.demo.repository.s_table_repo_S; //gives access to the query
import org.springframework.web.bind.annotation.*; //give access to the get mapping and rest controller

import java.util.List;

@RestController 
@RequestMapping("/api/S_table_S") //this sets the base URL for all endpoints in this controller
public class s_table_S_controller {
    
private final s_table_repo_S repo; //this is the repository that will be used to access the database

public s_table_S_controller(s_table_repo_S repo){

    this.repo = repo; //this is the constructor that will be used to inject the repository

}

@GetMapping 
public List<Object[]> getstaff(){

    return repo.findAllNames(); //this is the method that will be used to get all the names from the database

}

}
