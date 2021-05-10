package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping(value="/")
    public String index(){
        return "index.html";
    }

    @GetMapping(value="/projekt-form")
    public String renderProjectForm(){
        return "opretProjekt.html";
    }

    @PostMapping(value="/opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description")String description,
                               @RequestParam("deadline") String deadline,
                               @RequestParam("nr-of-tasks") int numberOfTasks, HttpServletRequest request){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Project newProject = new Project(projectName,description,parsed);
        session.setAttribute("newProject",newProject);
        session.setAttribute("number-of-tasks",numberOfTasks);

            return "redirect:/render-task-form";

    }

    @GetMapping(value="/render-task-form")
    public String renderTaskForm(Model tasks,HttpServletRequest request){
        HttpSession session = request.getSession();
        int[] nrOfTasks = new int[(int) session.getAttribute("number-of-tasks")];
        for (int i = 0; i < nrOfTasks.length; i++) {
            nrOfTasks[i] = i+1;
        }

        tasks.addAttribute("tasks",nrOfTasks);
        return "addTasks.html";
    }

   @PostMapping(value="/create-tasks")
    public String createTasks(@RequestParam("task")String[] names,HttpServletRequest request){
       HttpSession session = request.getSession();

       Project newProj = (Project) session.getAttribute("newProject");


       return "redirect:/";
    }
}
