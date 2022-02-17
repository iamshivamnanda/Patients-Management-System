package com.shivam.pms.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Appointments {
    @Id
    @GeneratedValue
    Integer aid;
    Timestamp datecreated;
    Timestamp appointmentdate;
    public Appointments(Integer aid, Timestamp datecreated, Timestamp appointmentdate) {
        this.aid = aid;
        this.datecreated = datecreated;
        this.appointmentdate = appointmentdate;
    }
    public Integer getAid() {
        return aid;
    }
    public void setAid(Integer aid) {
        this.aid = aid;
    }
    public Timestamp getDatecreated() {
        return datecreated;
    }
    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }
    public Timestamp getAppointmentdate() {
        return appointmentdate;
    }
    public void setAppointmentdate(Timestamp appointmentdate) {
        this.appointmentdate = appointmentdate;
    }


}
