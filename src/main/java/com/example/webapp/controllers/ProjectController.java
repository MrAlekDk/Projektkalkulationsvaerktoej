package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.services.ProjectService;
import com.example.webapp.services.SubTaskService;
import com.example.webapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ProjectController {


    @GetMapping(value = "/projekt-form")
    public String renderProjectForm() {
        return "opretProjekt.html";
    }

    @PostMapping(value = "opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description") String description,
                               @RequestParam("deadline") String deadline, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Project newProject = new Project(projectName, description, parsed);

        ProjectService projSer = new ProjectService();
        projSer.makeProject(newProject);



        return "redirect:/render-all-projects";
    }

    @GetMapping(value="render-all-projects")
    public String renderAllProjects(Model model, HttpServletRequest request) {

        ProjectService proService = new ProjectService();
        model.addAttribute("projectList",proService.getAllProjectS());

        return "allProjectsView.html";
    }


    @GetMapping(value ="/renderProject/{projectID}")
    public String renderProject(Model model, @PathVariable("projectID") int projectID) {

        ProjectService projSer = new ProjectService();
        Project tmpProject = projSer.getSpecificProject(projectID);
        model.addAttribute("project",tmpProject);

        TaskService tService = new TaskService();
        ArrayList<Task> allTasks = tService.getAllTasks(projectID);

        SubTaskService stService= new SubTaskService();
        stService= new SubTaskService();
        ArrayList<Task> allTasks2 = new ArrayList<>();
        allTasks2 = stService.getAllSubTasks(allTasks);

        model.addAttribute("tasklist", allTasks);


        return "projectview.html";
    }


}
