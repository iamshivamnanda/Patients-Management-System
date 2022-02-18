package com.shivam.pms.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.shivam.pms.model.Appointments;
import com.shivam.pms.model.Patient;
import com.shivam.pms.repository.AppointmentsRepository;
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
@RequestMapping("/Appointments")
public class appointmentscontroller {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @GetMapping("/allAppointments")
    public String getAllAppointments(Model model) {

        try {
            System.out.println(
                    appointmentsRepository.count()

            );
            ArrayList<Appointments> appointments = new ArrayList<>();
            appointmentsRepository.findAll().forEach(app -> appointments.add(app));
            model.addAttribute("appointments", appointments);
            model.addAttribute("title", "AppointMents");

        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "SOMETHING WENT WRONG");
        }
        return "appointments";
    }

    @GetMapping("/todayAppointments")
    public String getTodayAppointments(Model model) {
        
        try {
            ArrayList<Appointments> appointments = new ArrayList<>();
            appointmentsRepository.findByAppointmentdate().forEach(app -> appointments.add(app));
            model.addAttribute("appointments", appointments);
            model.addAttribute("title", "Today's AppointMents");

        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "SOMETHING WENT WRONG");
        }
        return "appointments";

    }

    @PostMapping("/add-appointment-page")
    public String getAddAppointmentPage(@RequestParam Integer id, Model model) {
        Patient patient = patientRepository.findById(id).get();
        ArrayList<Appointments> appointments = new ArrayList<>();
        appointmentsRepository.findByPid(id).forEach(app -> appointments.add(app));
        System.out.println(appointments);
        model.addAttribute("appointments", appointments);
        model.addAttribute("patient", patient);
        return "add-appointment";
    }

    @PostMapping("/bookAppointment")
    public String bookAppointmentt(@RequestParam Integer id, @RequestParam String desc,
            @RequestParam String dateTime, Model model) {
        try {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Date date = (Date) formatter.parse(dateTime);
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            Date curdate = new Date();
            Timestamp curTimestamp = new Timestamp(curdate.getTime());
            // Patient patient = new Patient(null, name, phoneNo, timestamp, "Booked");
            // patientRepository.save(patient);
            System.out.println("[BOOKAPPOINTMENT  ID]" + id);
            System.out.println("[BOOKAPPOINTMENT  timestamp]" + timestamp);
            System.out.println("SAVED");
            ;
            Patient patient = patientRepository.findById(id).get();
            Appointments appointment = new Appointments(null, curTimestamp, timestamp, id, desc, patient.getName(), "Booked");
            appointmentsRepository.save(appointment);
            model.addAttribute("currDateTime", new Date());
            model.addAttribute("message", patient.getName() + "'s Apoointment is Booked." );
        } catch (Exception e) {
            System.out.println(e);
        }

        return "bookedStatus";
    }

    @PostMapping("/del-appointment")
    public String delAppointment(@RequestParam Integer id,Model model){
        try {
            appointmentsRepository.deleteById(id);
            model.addAttribute("currDateTime", new Date());
            model.addAttribute("message", "Appointment Deleted");
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("message", "Something went wrong");
        }
        return "bookedStatus";
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam Integer modelid, @RequestParam String status,@RequestParam String dateTime,Model model){
        try {
            System.out.println( "UPDATE STATUS  " + modelid);
            System.out.println( "UPDATE STATUS  " + status);
            Appointments appointment = appointmentsRepository.findById(modelid).get();
            appointment.setStatus(status);

            if(status.equalsIgnoreCase("update")){
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                Date date = (Date) formatter.parse(dateTime);
                LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Timestamp timestamp = Timestamp.valueOf(localDateTime);
                System.out.println( "UPDATE STATUS  " + timestamp);
                appointment.setAppointmentdate(timestamp);
            }
            appointmentsRepository.save(appointment);
            model.addAttribute("currDateTime", new Date());
            model.addAttribute("message", "Status Changed");
        } catch (Exception e) {
            model.addAttribute("message", "Something went wrong");
        }
        return "bookedStatus";
    }
}
