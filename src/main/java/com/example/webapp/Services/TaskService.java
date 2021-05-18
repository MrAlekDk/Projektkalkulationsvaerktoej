package com.example.webapp.Services;

import com.example.webapp.models.Task;
import com.example.webapp.repository.TaskRep;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class TaskService {
    private TaskRep taskRep;


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
    public LocalDate getTaskStartDate(ArrayList<Task> taskID) {

        ArrayList<LocalDate> allStartDates = new ArrayList<>();

        for (int i = 0; i < taskID.size(); i++) {
            allStartDates.add(taskID.get(i).getStartDate());
        }
            Collections.sort(allStartDates);
            LocalDate firstStartDate = allStartDates.get(0);
        System.out.println(firstStartDate);
            return firstStartDate;
    }

}
