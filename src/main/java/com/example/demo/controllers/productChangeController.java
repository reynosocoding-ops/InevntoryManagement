package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productChangeController {
    @GetMapping("notEnoughParts")
    public String notEnoughParts(Model theModel){
        return "notEnoughParts";
    }

    @GetMapping("TooManyParts")
    public String TooManyParts(Model theModel){
        return "TooManyParts";
    }
}
