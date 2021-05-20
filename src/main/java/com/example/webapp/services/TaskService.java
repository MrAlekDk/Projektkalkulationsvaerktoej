package com.example.webapp.services;

import com.example.webapp.models.Task;
import com.example.webapp.models.TaskCache;
import com.example.webapp.repository.TaskRep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TaskService {
    private TaskRep taskRep;
    private TaskCache taskCache;
    private SubTaskService stService;


    public TaskService(){
        this.taskRep = new TaskRep();
        taskCache = new TaskCache();
        stService = new SubTaskService();
    }

    public boolean addTask(String name, String desc, int projectID, String startDate, int duration, String deadline) {
        Task newTask = new Task(name, desc, projectID, startDate, duration, deadline);
        Date dateNow = new Date();
        if(newTask.getTaskDeadline().before(newTask.getStartDate())||newTask.getStartDate().before(dateNow)){
            return false;
        }
        else{
            this.taskRep.addTask(newTask);
            return true;
        }
    }

   /* public int calculateProjectDuration(ArrayList<Task> taskID){
        int totalDuration = 0;
        for (int i = 0; i < taskID.size(); i++) {
            for (int j = 0; j < taskID.get(i).getSubtasks().size(); j++) {
                totalDuration += taskID.get(i).getSubtasks().get(j).getDuration();
            }
        }
        return totalDuration;
    }*/

    public ArrayList<Task> getAllTasks(int projectID) {

        if(taskCache.hasTasks(projectID)){
            return taskCache.getAllTask(projectID);
        }
        else{
            ArrayList<Task> taskListWithSubtasks = taskRep.getAllTasks(projectID);
            if(taskListWithSubtasks.isEmpty()){
                return null;
            }
            taskListWithSubtasks = stService.getAllSubTasks(taskListWithSubtasks);
            taskListWithSubtasks = orderTaskStartDate(taskListWithSubtasks);
            taskCache.setMapOfTask(taskListWithSubtasks);
            return taskCache.getAllTask(projectID);
        }
        //return taskRep.getAllTasks(projectID);
    }

    public ArrayList<Task> orderTaskStartDate(ArrayList<Task> taskID) {

        ArrayList<Date> allStartDates = new ArrayList<>();

        for (int i = 0; i < taskID.size(); i++) {
            allStartDates.add(taskID.get(i).getStartDate());
        }
            Collections.sort(allStartDates);
            //Date firstStartDate = allStartDates.get(0);
            return taskID;
    }

    public void updateCache(int projectID){
        ArrayList<Task> taskListWithSubtasks = taskRep.getAllTasks(projectID);
        taskListWithSubtasks = stService.getAllSubTasks(taskListWithSubtasks);
        taskListWithSubtasks = orderTaskStartDate(taskListWithSubtasks);
        taskCache.setMapOfTask(taskListWithSubtasks);
    }

}
