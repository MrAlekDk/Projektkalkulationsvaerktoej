package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
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


    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

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
        session.setAttribute("newProject", newProject);

        return "redirect:/renderProject";

    }

    @GetMapping(value = "/renderProject")
    public String renderProject(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("project", (Project) session.getAttribute("newProject"));

        Project tmpProject = (Project) session.getAttribute("newProject");
        model.addAttribute("tasklist", tmpProject.getTasks());

        return "projectview.html";
    }

    @GetMapping(value = "/render-task-form")
    public String renderTaskForm() {
        return "taskForm.html";
    }

    @PostMapping(value = "/create-task")
    public String createTasks(@RequestParam("task-name") String taskName, @RequestParam("task-description") String taskDesc, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Task newTask = new Task(taskName, taskDesc);
        Project newProj = (Project) session.getAttribute("newProject");
        newProj.addTask(newTask);
        return "redirect:/renderProject";
    }

    @GetMapping(value = "/render-subtask-form")
    public String renderSubtaskForm(@RequestParam("create-subtask")String taskName,HttpServletRequest request){
        System.out.println(taskName);
        HttpSession session = request.getSession();
        session.setAttribute("task-editing", taskName);

        return "subtaskForm.html";
    }

    @PostMapping(value="create-subtask")
    public String createSubtask(@RequestParam("subtask-name") String subtaskName, @RequestParam("subtask-description") String subtaskDesc,HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println((String)session.getAttribute("task-editing"));
        System.out.println(subtaskName);
        System.out.println(subtaskDesc);

        SubTask newSubTask = new SubTask(subtaskName, subtaskDesc);
        Project newProj = (Project) session.getAttribute("newProject");

        newProj.addSubtask(newSubTask,(String)session.getAttribute("task-editing"));

        return "redirect:/renderProject";
    }

}
