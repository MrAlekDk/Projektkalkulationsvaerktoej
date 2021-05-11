package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping(value = "/")
    public String index() {
        return "index.html";
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
        newProj.setTask(newTask);
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

        SubTask newSubTask = new SubTask(subtaskName, subtaskDesc);
        Project newProj = (Project) session.getAttribute("newProject");

        newProj.setSubtask(newSubTask,(String)session.getAttribute("task-editing"));

        return "redirect:/renderProject";
    }

}
