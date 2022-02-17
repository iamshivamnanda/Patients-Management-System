package com.shivam.pms.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivam.pms.model.Patient;
import com.shivam.pms.repository.PatientRepository;

@Controller
public class controller {
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/")
    public String getindex(Model model) {
        model.addAttribute("currDateTime", new Date());
        return "index";
    }

    @PostMapping("/bookAppointment")
    public String bookAppointmentt(@RequestParam String name, @RequestParam String phoneNo,
            @RequestParam String dateTime, Model model) {
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date date = (Date) formatter.parse(dateTime);
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            Patient patient = new Patient(null, name, phoneNo, timestamp, "Booked");
            patientRepository.save(patient);
            System.out.println("SAVED");
        } catch (Exception e) {
            System.out.println(e);
        }

        return "bookedStatus";
    }

    @GetMapping("/patients")
    public ResponseEntity<ArrayList<Patient>> getPatients() {
        try {
            ArrayList<Patient> patients = new ArrayList<Patient>();
            patientRepository.findAll().forEach((patient) -> patients.add(patient));
            return new ResponseEntity<ArrayList<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Appointments")
    public ResponseEntity<ArrayList<Patient>> getAppointments(){
        try {
            ArrayList<Patient> patients = new ArrayList<Patient>();
           patientRepository.findByOrderByName().forEach(patient -> patients.add(patient));
           return new ResponseEntity<ArrayList<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todayAppointments")
    public ResponseEntity<ArrayList<Patient>> todayAppointments(){
        try {
            ArrayList<Patient> patients = new ArrayList<Patient>();
           patientRepository.findByOrderByName().forEach(patient -> patients.add(patient));
           return new ResponseEntity<ArrayList<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
