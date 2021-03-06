package com.example.webapp.controllers;
// @Author Alexander J.P.M Sørensen
import com.example.webapp.services.SubTaskService;
import com.example.webapp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TaskController {
    private TaskService taskService = new TaskService();
    private SubTaskService subTaskService = new SubTaskService();


    @GetMapping(value = "/render-task-form/{projectID}")
    public String renderTaskForm(@PathVariable("projectID") int projectID, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            model.addAttribute("projectID", projectID);
            return "taskform.html";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/create-task")
    public String createTasks(@RequestParam("projectID") int projectID,
                              @RequestParam("task-name") String taskName,
                              @RequestParam("task-description") String taskDesc,
                              @RequestParam("task-startdate") String startDate,
                              @RequestParam("task-duration") int duration,
                              @RequestParam("task-deadline") String deadline,
                                          Model model) {


        boolean taskCreated = taskService.addTask(taskName, taskDesc, projectID, startDate, duration, deadline);
        if (taskCreated) {
            return "redirect:/update-cache/" + projectID;
        } else {
            model.addAttribute("projectID", projectID);
            return "taskNotCreated.html";
        }
    }


    @GetMapping(value = "/render-subtask-form/{projectID}/{taskID}")
    public String renderSubtaskForm(@PathVariable("projectID") int projectID,
                                    @PathVariable("taskID") int taskID,
                                    Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            model.addAttribute("taskID", taskID);
            model.addAttribute("projectID", projectID);
            return "subtaskForm.html";
        } else {
            return "redirect/";
        }
    }

    @PostMapping(value = "create-subtask")
    public String createSubtask(@RequestParam("projectID") int projectID,
                                @RequestParam("taskID") int taskID,
                                @RequestParam("subtask-name") String taskName,
                                @RequestParam("subtask-description") String taskDesc,
                                @RequestParam("subtask-worker") int workerID,
                                @RequestParam("subtask-start-date") String startDate,
                                @RequestParam("subtask-duration") int duration,
                                @RequestParam("subtask-deadline") String deadline, Model model) {

            boolean sTaskCreated = subTaskService.addSubtask(taskName, taskDesc, workerID, taskID, startDate, duration, deadline);
            if (sTaskCreated) {
                return "redirect:/update-cache/" + projectID;
            } else {
                model.addAttribute("projectID", projectID);
                model.addAttribute("taskID", taskID);
                return "subTaskNotCreated.html";
            }
        }
    }
