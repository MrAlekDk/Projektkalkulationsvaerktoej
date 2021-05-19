package com.example.webapp.controllers;

import com.example.webapp.models.Cache;
import com.example.webapp.models.Project;
import com.example.webapp.Services.ProjectService;
import com.example.webapp.services.SubTaskService;
import com.example.webapp.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProjectController {
    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private SubTaskService subTaskService = new SubTaskService();

    @GetMapping(value = "/projekt-form")
    public String renderProjectForm() {
        return "opretProjekt.html";
    }

    @PostMapping(value = "/opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description") String description,
                               @RequestParam("deadline") String deadline) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Project newProject = new Project(projectName, description, parsed);
        projectService = new ProjectService();
        boolean projectCreated = projectService.makeProject(newProject);

        if (projectCreated) {
            return "redirect:/render-all-projects";
        } else {
            return "redirect:/project-not-created";
        }
    }

    @GetMapping(value = "/project-not-created")
    public String projectNotCreated() {
        return "projectNotCreated.html";
    }

    @GetMapping(value = "/update-cache/{projectID}")
    public String updateCache(@PathVariable("projectID") int projectID) {

        return "redirect:/renderProject/" + projectID;
    }

    @GetMapping(value = "render-all-projects")
    public String renderAllProjects(Model model, HttpServletRequest request) {
        model.addAttribute("projectList", projectService.getAllProjects());

        return "allProjectsView.html";
    }


    @GetMapping(value = "/renderProject/{projectID}")
    public String renderProject(Model model, @PathVariable("projectID") int projectID) {
        model.addAttribute("project", projectService.getSpecificProject(projectID));
        model.addAttribute("tasklist", subTaskService.getAllSubTasks(taskService.getAllTasks(projectID)));

        return "projectview.html";
    }

}
