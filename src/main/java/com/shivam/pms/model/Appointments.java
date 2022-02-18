package com.shivam.pms.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// @NamedNativeQueries({
//     @NamedNativeQuery(
//         name = "Appointments.findAllAppointments",
//         query =
//             "SELECT * " +
//             "FROM Appointments ", resultClass = Appointments.class
//     ),
//     // @NamedNativeQuery(
//     //     name = "Person.findByAppointmentdate",
//     //     query =
//     //         "SELECT * " +
//     //         "FROM Appointments a " +
//     //         "WHERE a.appointmentdate >= CURDATE()", resultClass = Appointments.class)

// })
@Entity
public class Appointments {
    @Id
    @GeneratedValue
    Integer aid;
    Timestamp datecreated;
    Timestamp appointmentdate;
    Integer pid;
    String appdesc;
    String patientname;
    String status;

    public Appointments() {
    }

    public Appointments(Integer aid, Timestamp datecreated, Timestamp appointmentdate, Integer pid, String appdesc,
            String patientname, String status) {
        this.aid = aid;
        this.datecreated = datecreated;
        this.appointmentdate = appointmentdate;
        this.pid = pid;
        this.appdesc = appdesc;
        this.patientname = patientname;
        this.status = status;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getAppdesc() {
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointments [aid=" + aid + ", appdesc=" + appdesc + ", appointmentdate=" + appointmentdate
                + ", datecreated=" + datecreated + ", patientname=" + patientname + ", pid=" + pid + ", status="
                + status + "]";
    }

    

}
