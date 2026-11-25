package com.example.demo.controller;

import com.example.demo.repository.s_table_repo; //gives access to the query
import org.springframework.web.bind.annotation.*; //give access to the get mapping and rest controller

import java.util.List;

@RestController //this tells springboot that this class handles http requests and responses
@RequestMapping("/api/s_table") //this sets the base URL for all endpoints in this controller
public class s_table_controller {
    
private final s_table_repo repo; //this is the repository that will be used to access the database

public s_table_controller(s_table_repo repo) {
    this.repo = repo; //this is the constructor that will be used to inject the repository

}

@GetMapping 
public List<Object[]> getstaff(){

    return repo.findAllNames(); //this is the method that will be used to get all the names from the database

}

}
