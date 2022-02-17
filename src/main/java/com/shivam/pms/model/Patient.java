package com.shivam.pms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Patient {
    
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String phoneno;

    @OneToMany(targetEntity = Appointments.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="pa_fk",referencedColumnName = "id")
    List<Appointments> appointments;

    public Patient() {
    }

    public Patient(Integer id, String name, String phoneno, List<Appointments> appointments) {
        this.id = id;
        this.name = name;
        this.phoneno = phoneno;
        this.appointments = appointments;
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

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }
    
}
