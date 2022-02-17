package com.shivam.pms.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    
    @GetMapping("/")
    public String getindex(Model model){
        model.addAttribute("currDateTime",new Date());
        return "index";
    }
}
