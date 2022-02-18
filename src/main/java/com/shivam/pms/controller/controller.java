package com.shivam.pms.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivam.pms.repository.AppointmentsRepository;
import com.shivam.pms.repository.PatientRepository;

@Controller
public class controller {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @GetMapping("/")
    public String getindex(Model model) {
        model.addAttribute("currDateTime", new Date());
        return "index";
    }

    @GetMapping("/insights")
    public String getinsights(Model model) {
        return "insight";
    }

    @PostMapping("/insights")
    public String insights(Model model, @RequestParam String dateTime) {
       

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) formatter.parse(dateTime);
            // LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            // Timestamp timestamp = Timestamp.valueOf(localDateTime);
            System.out.println(dateTime);
            long lo[] = new long[4];
            long l;
           l= appointmentsRepository.countByStatus("booked",date);
           lo[1] =l;
           l = appointmentsRepository.countByStatus("seen",date);
            lo[0] =l;
            l = appointmentsRepository.countByStatus("update",date);
            lo[2] =l;
            l = appointmentsRepository.countByStatus("cancelled",date);
            lo[3] =l;
            System.out.println(lo[0]);
            System.out.println(lo[1]);
            System.out.println(lo[2]);
            System.out.println(lo[3]);
            model.addAttribute("values", lo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("[INSIGHTS] ");
        return "insight";
    }

}
