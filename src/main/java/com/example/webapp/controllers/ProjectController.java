package com.example.webapp.controllers;

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

        boolean projectCreated = projectService.makeProject(projectName,description,deadline);

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
        projectService.updateCache();
        taskService.updateCache(projectID);
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
        model.addAttribute("tasklist", taskService.getAllTasks(projectID));
        int gnsTimer = projectService.getDailyWorkHours(taskService.getAllTasks(projectID),projectService.getSpecificProject(projectID).getDeadline());
        model.addAttribute("gnstimer", gnsTimer);
        model.addAttribute("feasible", projectService.getIfFeasible(gnsTimer));
        model.addAttribute("projectprice",projectService.calculateProjectPrice(taskService.getAllTasks(projectID)));

        return "projectview.html";
    }

}
