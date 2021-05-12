package com.example.webapp.services;

import com.example.webapp.models.Task;
import com.example.webapp.repository.TaskRep;

import java.util.ArrayList;

public class TaskService {
    private TaskRep taskRep;


    public TaskService(){
        this.taskRep = new TaskRep();

    }


    public void addTask(Task newTask) {

    }

    public ArrayList<Task> getAllTasks(int projectID) {
        return taskRep.getAllTasks(projectID);
    }
}
