package com.example.webapp.services;

import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.repository.SubTaskRep;
import com.example.webapp.repository.TaskRep;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SubTaskService {

    private SubTaskRep subTaskRep;


    public SubTaskService(){
        this.subTaskRep = new SubTaskRep();

    }


    public void addTask(SubTask newSubTask) {
        this.subTaskRep.addSubTask(newSubTask);
    }

    public ArrayList<Task> getAllSubTasks(ArrayList<Task> allTask) {


        for (int i = 0; i < allTask.size(); i++) {
            int tmpTaskID =allTask.get(i).getTaskID();
            allTask.get(i).addSubtask(subTaskRep.getAllSubTasks(tmpTaskID));
        }
        return allTask;
    }
}
