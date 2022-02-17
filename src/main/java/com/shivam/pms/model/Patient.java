package com.shivam.pms.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;
    String phoneno;
    Timestamp datetime;
    String status;
    public Patient() {
    }

    public Patient(Integer id, String name, String phoneno, Timestamp datetime, String status) {
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
        this.datetime = datetime;
        this.status = status;
    }

    public Integer getPid() {
        return id;
    }

    public void setPid(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getphoneno() {
        return phoneno;
    }

    public void setphoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Timestamp getdatetime() {
        return datetime;
    }

    public void setdatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient [datetime=" + datetime + ", id=" + id + ", name=" + name + ", phoneno=" + phoneno + ", status="
                + status + "]";
    }
    
    
}
