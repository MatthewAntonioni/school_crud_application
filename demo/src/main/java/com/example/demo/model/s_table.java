package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate; //if you have a database entry that uses the date data type you need this or the server will crash

@Entity 
@Table(name = "staff") //table im referncing name
public class s_table {
    
@Id //primary key
private int ID;

private String Email;
private String Name;
private String Password;
private String Position;
private LocalDate Date_hired;

}

//entry must match what database looks like no matter if were using it all or not