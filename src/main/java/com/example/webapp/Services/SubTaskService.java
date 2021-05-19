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


    public boolean addTask(String name, String desc, int workerID, int taskID, String startDate,int duration, String deadline) {

        SubTask newSubTask = new SubTask(name, desc, workerID, taskID, startDate, duration, deadline);
        if(newSubTask.getDeadline().before(newSubTask.getStart())){
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
