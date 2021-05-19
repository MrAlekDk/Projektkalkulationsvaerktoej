package com.example.webapp.services;

import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.repository.SubTaskRep;

import java.util.ArrayList;
import java.util.Date;

public class SubTaskService {

    private SubTaskRep subTaskRep;


    public SubTaskService(){
        this.subTaskRep = new SubTaskRep();

    }


    public boolean addTask(String name, String desc, int workerID, int taskID, String startDate,int duration, String deadline) {

        SubTask newSubTask = new SubTask(name, desc, workerID, taskID, startDate, duration, deadline);
        Date dateNow = new Date();
        if(newSubTask.getDeadline().before(newSubTask.getStart())||newSubTask.getStart().before(dateNow)){
            return false;
        }
        else{
            this.subTaskRep.addSubTask(newSubTask);
            return true;
        }
    }

    public ArrayList<Task> getAllSubTasks(ArrayList<Task> allTask) {

        ArrayList<SubTask> allSubTasks= subTaskRep.getAllSubTasks();
        if(allSubTasks.isEmpty()){
            return allTask;
        }

        for (int i = 0; i < allTask.size(); i++) {
            for (int j = 0; j < allSubTasks.size(); j++) {
                if(allTask.get(i).getTaskID()==allSubTasks.get(j).getTaskID()){
                    allTask.get(i).addSubtask(allSubTasks.get(j));
                }
            }

        }
        return allTask;
    }

}
