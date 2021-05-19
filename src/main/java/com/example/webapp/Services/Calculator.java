package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Calculator {
        private int hourPrice = 950;
        private int WeeklyWorkHours = 37;
        TaskService tService = new TaskService();

        public int getPriceForProject(int projectHours){
            int projectPrice = hourPrice * projectHours;
            return projectPrice;
        }
/*
        public long timeForTasks(Task task){
            LocalDate start = task.getStartDate();
            LocalDate deadline = task.getTaskDeadline();
            long daysForProject = Duration.between(start, deadline).toDays();
            return daysForProject;
        }

        public int dailyWorkHours(Project project){
            int projectDuration = tService.calculateDuration(project.getTasks());
            //int daysForProject = Integer.parseInt(String.valueOf(Duration.between(project.getTasks().get(0).getStartDate(), project.getDeadline()).toDays()));
            long days = Duration.between(project.getTasks().get(0).getStartDate().atStartOfDay(), project.getDeadline().atStartOfDay()).toDays();
            System.out.println(project.getTasks());
            System.out.println();
            //int dailyWorkHours = projectDuration/daysForProject;
           // System.out.println(dailyWorkHours);
            return 1;
        }

 */
         //Droppede vi number of participants?


}