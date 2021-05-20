package com.example.webapp.controllers;

import com.example.webapp.models.Project;
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
import java.util.ArrayList;


@Controller
public class ProjectController {
    private ProjectService projectService = new ProjectService();
    private TaskService taskService = new TaskService();
    private SubTaskService subTaskService = new SubTaskService();

    @GetMapping(value = "/projekt-form")
    public String renderProjectForm(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "opretProjekt.html";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description") String description,
                               @RequestParam("deadline") String deadline) {

        boolean projectCreated = projectService.makeProject(projectName, description, deadline);

        if (projectCreated) {
            return "redirect:/render-all-projects";
        } else {
            return "redirect:/project-not-created";
        }
    }

    @GetMapping(value = "/project-not-created")
    public String projectNotCreated(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            return "projectNotCreated.html";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/update-cache/{projectID}")
    public String updateCache(@PathVariable("projectID") int projectID, HttpServletRequest request) {
        projectService.updateCache();
        taskService.updateCache(projectID);
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "redirect:/renderProject/" + projectID;
        } else {
            return "redirect/";
        }
    }

    @GetMapping(value = "render-all-projects")
    public String renderAllProjects(Model model, HttpServletRequest request) {
        model.addAttribute("projectList", projectService.getAllProjects());

        return "allProjectsView.html";
    }


    @GetMapping(value = "/renderProject/{projectID}")
    public String renderProject(Model model, @PathVariable("projectID") int projectID, HttpServletRequest request) {
        model.addAttribute("project", projectService.getSpecificProject(projectID));
        model.addAttribute("tasklist", taskService.getAllTasks(projectID));
        model.addAttribute("gnstimer", projectService.getDailyWorkHours(taskService.getAllTasks(projectID), projectService.getSpecificProject(projectID).getDeadline()));
        model.addAttribute("projectprice", projectService.calculateProjectPrice(taskService.getAllTasks(projectID)));

        HttpSession session = request.getSession(false);
        if (session != null) {
            return "projectview.html";
        } else {
            return "redirect:/";
        }
    }
}
