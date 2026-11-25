package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "staff") //table im referncing name
public class s_table {
    
@Id //primary key
private int ID;

private String Email;
private String Name;
private String Password;
private String Postiton;
private String Date_hired;

}

//entry must match what database looks like no matter if were using it all or not