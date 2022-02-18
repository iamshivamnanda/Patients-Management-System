package com.shivam.pms.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import com.shivam.pms.model.Patient;
import com.shivam.pms.repository.AppointmentsRepository;
import com.shivam.pms.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AppointmentsRepository appointmentsRepository;

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
        int id = patientRepository.save(patient).getId();
        System.out.println("[NEW PATIENT ID]  " + id);
        patient.setId(id);
        model.addAttribute("patient", patient);
        return "add-appointment";
    }

    @PostMapping("/del-patient")
    @Transactional
    public String postDelPatient(@RequestParam Integer id, Model model) {
        appointmentsRepository.deleteByPid(id);
        patientRepository.deleteById(id);
        System.out.println("[DELETED]");
        return "bookedStatus";
    }

    @PostMapping("/edit-patient")
    public String editPatientPage(@RequestParam Integer id, Model model) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "edit-patient";
    }
    

    @PostMapping(path = "/update-patient")
    public String updatePatient(@RequestParam Integer id,
            @RequestParam String name, @RequestParam String phoneNo,Model model) {
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(name);
        patient.setPhoneno(phoneNo);
        patientRepository.save(patient);
        model.addAttribute("message", patient.getName() +"'s Details Updated");
        model.addAttribute("currDateTime", new Date());
        return "bookedStatus";
    }
}
