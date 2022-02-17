package com.shivam.pms.controller;

import java.util.ArrayList;

import com.shivam.pms.model.Patient;
import com.shivam.pms.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patient")
public class patientcontroller {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/add-patient")
    public String getAddPatient() {
        return "add-patient";
    }

    @GetMapping("/showpatients")
    public String getPatients(Model model) {
        try {
            ArrayList<Patient> patients = new ArrayList<Patient>();
            patientRepository.findAll().forEach((patient) -> patients.add(patient));
            model.addAttribute("patients", patients);
            // return new ResponseEntity<ArrayList<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("[AppController]: Patients Failed to Get: " + e);
            e.printStackTrace();
            // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "patients";
    }

    @PostMapping("/addnew-patient")
    public String postAddPatient(@RequestParam String name, @RequestParam String phoneNo, Model model) {
        System.out.println("NAME :" + name);
        System.out.println("phoneNo :" + phoneNo);
        Patient patient = new Patient();
        patient.setName(name);
        patient.setPhoneno(phoneNo);
        patientRepository.save(patient);
        System.out.println("[SAVED]");

        return "bookedStatus";
    }

    @PostMapping("/del-patient")
    public String postDelPatient(@RequestParam Integer id, Model model) {
        patientRepository.deleteById(id);
        System.out.println("[DELETED]");
        return "bookedStatus";
    }

    @PostMapping(path = "/update/{id}")
    public String updatePatient(@RequestParam Integer id,
            @RequestParam String name, @RequestParam String phoneNo) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(name);
        patient.setPhoneno(phoneNo);
        patientRepository.save(patient);
        return "bookedStatus";
    }
}
