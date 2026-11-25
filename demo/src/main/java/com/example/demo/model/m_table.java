package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity 
@Table(name = "member")
public class m_table {
    
@Id
private int ID;

private String Password;
private String Name;
private LocalDate Date_joined;
private String Status;

}
