package com.example.webapp.controllers;

import com.example.webapp.Services.Calculator;
import com.example.webapp.Services.ProjectService;
import com.example.webapp.Services.TaskService;
import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import com.example.webapp.models.User;
import com.example.webapp.repository.TaskRep;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {

    TaskService test = new TaskService();
    Calculator tester = new Calculator();

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

        TaskService tService = new TaskService();
        ArrayList<Task> allTasks = tService.getAllTasks(1);

        com.example.webapp.services.SubTaskService stService = new com.example.webapp.services.SubTaskService();
        stService = new com.example.webapp.services.SubTaskService();

        allTasks = stService.getAllSubTasks(allTasks);

        tService.calculateDuration(allTasks);
        test.calculateDuration(allTasks);
        tester.timeForTasks(allTasks.get(13));
        return "index.html";
    }
}
