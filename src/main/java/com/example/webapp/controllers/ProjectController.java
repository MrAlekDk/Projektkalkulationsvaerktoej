package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProjectController {


    @GetMapping(value = "/projekt-form")
    public String renderProjectForm() {
        return "opretProjekt.html";
    }

    @PostMapping(value = "/opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description") String description,
                               @RequestParam("deadline") String deadline, HttpServletRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Project newProject = new Project(projectName, description, parsed);


        ProjectService projSer = new ProjectService();
        projSer.makeProject(newProject);


        session.setAttribute("newProject", newProject);

        return "redirect:/renderProject";
    }

    @GetMapping(value="/render-all-projects")
    public String renderAllProjects(Model model, HttpServletRequest request) {

        ProjectService projSer = new ProjectService();

        model.addAttribute("projectList",projSer.getAllProjectS());


        return "allProjectsView.html";
    }


    @GetMapping(value = "/renderProject")
    public String renderProject(@RequestParam("selected-project")int projectID, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();


        ProjectService projSer = new ProjectService();
        Project tmpProject = projSer.getSpecificProject(projectID);

        model.addAttribute("project",tmpProject);

        model.addAttribute("tasklist", tmpProject.getTasks());

        return "projectview.html";
    }


}
