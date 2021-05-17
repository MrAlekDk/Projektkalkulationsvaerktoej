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
        this.taskRep.addTask(newTask);
    }

    public void calculateDuration(ArrayList<Task> taskID){

        int totalDuration = 0;

        for (int i = 0; i < taskID.size(); i++) {
            totalDuration += taskID.get(i).getNrOfHours();
            System.out.println(totalDuration);
        }
    }

    public ArrayList<Task> getAllTasks(int projectID) {
        return taskRep.getAllTasks(projectID);
    }
}
