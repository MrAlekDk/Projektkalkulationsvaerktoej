package com.example.webapp.controllers;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class TaskController {

    @GetMapping(value = "/render-task-form")
    public String renderTaskForm() {
        return "taskForm.html";
    }

    @PostMapping(value = "/create-task")
    public String createTasks(@RequestParam("task-name") String taskName, @RequestParam("task-description") String taskDesc,
                              @RequestParam("task-start-date") String startDate,
                              @RequestParam("task-duration")int duration,@RequestParam("task-deadline")String deadline,
                              HttpServletRequest request) {

        HttpSession session = request.getSession();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedStart = null;
        try {
            parsedStart = format.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date parsedDeadline = null;
        try {
            parsedDeadline = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int projectID = (Integer) session.getAttribute("selected-projectID");
        Task newTask = new Task(taskName, taskDesc, projectID, parsedStart,duration,parsedDeadline);
        TaskService tService = new TaskService();
        tService.addTask(newTask);

        session.setAttribute("projectTasks",tService.getAllTasks(projectID));

        return "redirect:/renderProject";
    }

    @GetMapping(value = "/render-subtask-form")
    public String renderSubtaskForm(@RequestParam("create-subtask")String taskName,HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("task-editing", taskName);

        return "subtaskForm.html";
    }

    @PostMapping(value="create-subtask")
    public String createSubtask(@RequestParam("subtask-name") String subtaskName, @RequestParam("subtask-description") String subtaskDesc,HttpServletRequest request){
        HttpSession session = request.getSession();

        return "redirect:/renderProject";
    }

    @PostMapping(value="/get-all-tasks")
    public String getAllTasks(@RequestParam("selected-project")int projectID, HttpServletRequest request){
        HttpSession session = request.getSession();

        TaskService tService = new TaskService();

        session.setAttribute("selected-projectID",projectID);
        session.setAttribute("projectTasks",tService.getAllTasks(projectID));


        return "redirect:/renderProject";
    }


}
