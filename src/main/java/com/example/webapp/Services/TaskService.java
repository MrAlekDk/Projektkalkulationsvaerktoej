package com.example.webapp.Services;

import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import com.example.webapp.repository.TaskRep;
import com.example.webapp.services.SubTaskService;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TaskService {
    private TaskRep taskRep;
    com.example.webapp.services.SubTaskService stService = new SubTaskService();


    public TaskService(){
        this.taskRep = new TaskRep();

    }

    public void addTask(Task newTask) {
        this.taskRep.addTask(newTask);
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

    public Date orderTaskStartDate(ArrayList<Task> taskID) {

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
