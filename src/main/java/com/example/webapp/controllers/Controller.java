package com.example.webapp.controllers;

import com.example.webapp.services.Calculator;
import com.example.webapp.services.TaskService;
import com.example.webapp.models.User;
import com.example.webapp.repository.ProjectRep;
import com.example.webapp.services.SubTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    ProjectRep rep = new ProjectRep();
    Calculator tester = new Calculator();
    TaskService test = new TaskService();
    com.example.webapp.services.SubTaskService yes = new SubTaskService();

    @GetMapping(value = "/")
    public String renderLoginPage() {
        return "login.html";
    }

    @PostMapping(value = "/check-login")
    public String checkLogin(@RequestParam("username") String mail, @RequestParam("password") String password) {
        User tmpUser = new User(mail,password);

        if(tmpUser.checkUser(mail,password)){

            return "redirect:/forside";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping(value = "/forside")
    public String index() {
        return "index.html";
    }
}
