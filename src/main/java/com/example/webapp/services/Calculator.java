package com.example.webapp.services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Calculator {
        private int hourPrice = 950;
        private int workDayHours = 8;
        TaskService tService = new TaskService();
        SubTaskService stService = new SubTaskService();

        public int getPriceForProject(int projectHours){
            int projectPrice = hourPrice * projectHours;
            return projectPrice;
        }

        public int hoursForProject(ArrayList<Task> allTask){

            Date startDate = allTask.get(0).getStartDate();
            Date deadline = allTask.get(allTask.size()-1).getTaskDeadline();

            LocalDate start = Instant.ofEpochMilli(startDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate dead = Instant.ofEpochMilli(deadline.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            Period period = Period.between(start, dead);
             int days = period.getDays();
             return days * workDayHours;
        }

        public int dailyWorkHours(ArrayList<Task> tasks, Date deadline){
            Date startDate = new Date();

            Calendar start = Calendar.getInstance();
            Calendar dead = Calendar.getInstance();

            start.setTime(startDate);
            dead.setTime(deadline);
            int days = 0;
            while (start.before(dead)){
                if (Calendar.SATURDAY != start.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != start.get(Calendar.DAY_OF_WEEK)){
                    days++;
                }
                start.add(Calendar.DATE,1);
            }

            int workHours = calculateProjectDuration(tasks);

           int dailyWorkHours = workHours/days;

            return dailyWorkHours;
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

    public String feasible(int dailyHours){
            if (dailyHours > workDayHours){
                return "This project is not feasible";
            }
            else {
                return "This project is feasible";
            }
    }

}