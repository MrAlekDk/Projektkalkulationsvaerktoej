package com.example.webapp.controllers;

import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.services.SubTaskService;
import com.example.webapp.Services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class TaskController {

    @GetMapping(value = "/render-task-form/{projectID}")
    public String renderTaskForm(@PathVariable("projectID")int projectID,Model model) {

        model.addAttribute("projectID",projectID);
        return "taskForm.html";
    }

    @PostMapping(value = "/create-task")
    public String createTasks(@RequestParam("projectID")int projectID,
                              @RequestParam("task-name") String taskName,
                              @RequestParam("task-description") String taskDesc,
                              @RequestParam("task-startdate") String startDate,
                              @RequestParam("task-duration")int duration,
                              @RequestParam("task-deadline")String deadline)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate parsedStart = LocalDate.parse(startDate, formatter);
        LocalDate parsedDeadline = LocalDate.parse(deadline, formatter);
        /*
        Date parsedstart= null;
        try {
            parsedstart = format.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date parseddeadline = null;
        try {
            parsedDeadline = format.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

         */

        Task newTask = new Task(taskName, taskDesc, projectID, parsedStart,duration,parsedDeadline);
        TaskService tService = new TaskService();
        tService.addTask(newTask);


        return "redirect:/update-cache/"+projectID;
    }

    @GetMapping(value = "/render-subtask-form/{projectID}/{taskID}")
    public String renderSubtaskForm(@PathVariable("projectID")int projectID, @PathVariable("taskID")int taskID,Model model){

        model.addAttribute("taskID",taskID);
        model.addAttribute("projectID",projectID);

        return "subtaskForm.html";
    }

    @PostMapping(value="create-subtask")
    public String createSubtask(@RequestParam("projectID")int projectID,
                                @RequestParam("taskID")int taskID,
                                @RequestParam("subtask-name") String taskName,
                                @RequestParam("subtask-description") String taskDesc,
                                @RequestParam("subtask-worker")int workerID,
                                @RequestParam("subtask-start-date")String startDate,
                                @RequestParam("subtask-duration")int duration,
                                @RequestParam("subtask-deadline")String deadline){

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

        SubTask newSubTask = new SubTask(taskName,taskDesc,workerID,taskID,parsedStart,duration,parsedDeadline);

        SubTaskService stService = new SubTaskService();
        stService.addTask(newSubTask);

        return "redirect:/update-cache/"+projectID;
    }


}
