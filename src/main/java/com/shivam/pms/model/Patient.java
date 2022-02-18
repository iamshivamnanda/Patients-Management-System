package com.shivam.pms.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Patient {
    
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String phoneno;

    public Patient() {
    }

    

    public Patient(Integer id, String name, String phoneno) {
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }



    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", phoneno=" + phoneno + "]";
    }

   
    
}
