package com.example.webapp.Services;

import com.example.webapp.models.Task;
import com.example.webapp.repository.TaskRep;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TaskService {
    private TaskRep taskRep;


    public TaskService(){
        this.taskRep = new TaskRep();

    }

    public boolean addTask(String name, String desc, int projectID, String startDate, int duration, String deadline) {

        Task newTask = new Task(name, desc, projectID, startDate, duration, deadline);
        if(newTask.getTaskDeadline().before(newTask.getStartDate())){
            return false;
        }
        else{
            this.taskRep.addTask(newTask);
            return true;
        }
    }

    public int calculateProjectDuration(ArrayList<Task> taskID){

        int totalDuration = 0;

        for (int i = 0; i < taskID.size(); i++) {
            for (int j = 0; j < taskID.get(i).getSubtasks().size(); j++) {
                totalDuration += taskID.get(i).getSubtasks().get(j).getDuration();
            }
        }
        return totalDuration;
    }

    public ArrayList<Task> getAllTasks(int projectID) {
        return taskRep.getAllTasks(projectID);
    }

    public Date getTaskStartDate(ArrayList<Task> taskID) {

        ArrayList<Date> allStartDates = new ArrayList<>();

        for (int i = 0; i < taskID.size(); i++) {
            allStartDates.add(taskID.get(i).getStartDate());
        }
            Collections.sort(allStartDates);
            Date firstStartDate = allStartDates.get(0);
        System.out.println(firstStartDate);
            return firstStartDate;
    }

}
