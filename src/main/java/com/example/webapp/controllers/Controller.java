package com.example.webapp.controllers;

import com.example.webapp.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String renderLoginPage() {
        return "login.html";
    }

    @PostMapping(value = "/check-login")
    public String checkLogin(@RequestParam("username") String mail, @RequestParam("password") String password) {
        User tmpUser = new User(mail,password);

        if(tmpUser.checkUser(mail,password)){
            System.out.println("den er her 1");
            return "redirect:/forside";
        }else{
            System.out.println("nummer 2");
            return "redirect:/";
        }
    }

    @GetMapping(value = "/forside")
    public String index() {
        return "index.html";
    }
}
