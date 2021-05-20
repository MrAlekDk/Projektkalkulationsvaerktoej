package com.example.webapp.controllers;

import com.example.webapp.services.Calculator;
import com.example.webapp.services.TaskService;
import com.example.webapp.models.User;
import com.example.webapp.repository.ProjectRep;
import com.example.webapp.services.SubTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    ProjectRep rep = new ProjectRep();
    Calculator tester = new Calculator();
    TaskService test = new TaskService();
    com.example.webapp.services.SubTaskService yes = new SubTaskService();

    @GetMapping(value = "/")
    public String renderLoginPage(HttpServletRequest request) {
            return "login.html";
        }

    @PostMapping(value = "/check-login")
    public String checkLogin(@RequestParam("username") String mail, @RequestParam("password") String password, HttpServletRequest request) {
        User tmpUser = new User(mail,password);

        if(tmpUser.checkUser(mail,password)){
            HttpSession session;
            session = request.getSession();
            session.setAttribute("username", tmpUser.getMail());
            session.setAttribute("password", tmpUser.getPassword());
            return "redirect:/forside";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping(value = "/forside")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "index.html";
        } else {
            return "redirect:/";
        }
    }
}
