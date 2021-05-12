package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping(value = "/")
    public String renderLoginPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(!session.isNew()){
            return "redirect:/forside";
        }
        return "login.html";
    }

    @PostMapping(value = "/check-login")
    public String checkLogin(@RequestParam("username") String mail, @RequestParam("password") String password, HttpServletRequest request) {
        User tmpUser = new User(mail,password);
        HttpSession session = request.getSession();
        if (tmpUser.checkUser(mail, password) == true) {
            session.setAttribute("username", tmpUser.getMail());
            session.setAttribute("password", tmpUser.getPassword());
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
