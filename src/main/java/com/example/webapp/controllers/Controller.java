package com.example.webapp.controllers;
// @Author Mette Marie H. Winther-Sørensen
import com.example.webapp.models.User;
import com.example.webapp.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    UserService tmp = new UserService();

    @GetMapping(value = "/")
    public String renderLoginPage() {
            return "login.html";
        }

    @PostMapping(value = "/check-login")
    public String checkLogin(@RequestParam("username") String mail, @RequestParam("password") String password, HttpServletRequest request) {
        User tmpUser = new User(mail,password);
        HttpSession session;
        if(tmp.checkUser(mail,password)){
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
