package com.shivam.pms.model;

import java.sql.Date;

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
    String phoneNo;
    Date dateTime;
    String status;
    public Patient() {
    }

    public Patient(int id, String name, String phoneNo, Date dateTime, String status) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.dateTime = dateTime;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient [dateTime=" + dateTime + ", id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", status="
                + status + "]";
    }
    
    
}
