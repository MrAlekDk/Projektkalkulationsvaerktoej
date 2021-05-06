package com.example.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping(value="/")
    @ResponseBody
    public String index(){
        return "Hvis det virker ser du dette";
    }
    
}
