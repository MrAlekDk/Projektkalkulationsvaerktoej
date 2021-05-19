package com.example.webapp.controllers;

import com.example.webapp.models.Cache;
import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
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
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ProjectController {

    Cache cache = new Cache();

    @GetMapping(value = "/projekt-form")
    public String renderProjectForm() {
        return "opretProjekt.html";
    }

    @PostMapping(value = "/opretProjekt")
    public String opretProjekt(@RequestParam("projectName") String projectName, @RequestParam("project-description") String description,
                               @RequestParam("deadline")String deadline) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Project newProject = new Project(projectName, description, parsed);
        ProjectService projSer = new ProjectService();
        boolean projectCreated= projSer.makeProject(newProject);

        if(projectCreated){
           return "redirect:/render-all-projects";
        }
        else{
            return "redirect:/project-not-created";
        }
    }

    @GetMapping(value="/project-not-created")
    public String projectNotCreated(){
        return "projectNotCreated.html";
    }

    @GetMapping(value="/update-cache/{projectID}")
    public String updateCache(@PathVariable("projectID")int projectID){

        /*Project tmpProject = cache.get(projectID);

        TaskService tService = new TaskService();
        ArrayList<Task> allTasks = tService.getAllTasks(projectID);

        SubTaskService stService = new SubTaskService();
        allTasks = stService.getAllSubTasks(allTasks);*/

        cache.set(projectID, cache.getProject(projectID));
        cache.setList(projectID, cache.getAllTasks(projectID));

        return "redirect:/renderProject/"+projectID;
    }

    @GetMapping(value="render-all-projects")
    public String renderAllProjects(Model model, HttpServletRequest request) {

        if(cache.hasProjects()){
            model.addAttribute("projectList",cache.getAllProjects());

        }
        else{
            ProjectService proService = new ProjectService();
            ArrayList<Project> tmp = proService.getAllProjectS();
            model.addAttribute("projectList",proService.getAllProjectS());
            cache.setProjects(tmp);
        }
        return "allProjectsView.html";
    }


    @GetMapping(value ="/renderProject/{projectID}")
    public String renderProject(Model model, @PathVariable("projectID") int projectID) {

        if (cache.has(projectID)) {
           model.addAttribute("project", cache.get(projectID));
           model.addAttribute("tasklist", cache.getList(projectID));

            return "projectview.html";
        } else {
            model.addAttribute("project", cache.getProject(projectID) );
            /*ProjectService projSer = new ProjectService();
            Project tmpProject = projSer.getSpecificProject(projectID);
            model.addAttribute("project", );

            TaskService tService = new TaskService();
            ArrayList<Task> allTasks = tService.getAllTasks(projectID);

            SubTaskService stService = new SubTaskService();

            allTasks = stService.getAllSubTasks(allTasks);

            tService.calculateProjectDuration(allTasks);*/
            model.addAttribute("tasklist", cache.getAllTasks(projectID));

            cache.set(projectID, cache.getProject(projectID));
            cache.setList(projectID, cache.getAllTasks(projectID));
            return "projectview.html";
        }
    }
}
