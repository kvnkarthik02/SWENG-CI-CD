package com.webCalc.springboot.demo;

import jdk.jfr.ContentType;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    @GetMapping("/")
    public String index(){
        return "redirect:/form";
    }

    @GetMapping("/form")
    public String formGet(){
        return "form";
    }

    @PostMapping("/form")
    public String formPost(Calculator calc, Model model){
        model.addAttribute("calc", calc);
        return "form";
    }
}
