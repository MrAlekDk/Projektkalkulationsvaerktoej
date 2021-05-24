package com.example.webapp.services;

import com.example.webapp.models.Task;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Calculator {
    private int hourPrice = 950;
    private int workDayHours = 8;


    public int getPriceForProject(int projectHours) {
        int projectPrice = hourPrice * projectHours;
        return projectPrice;
    }
    public int dailyWorkHours(ArrayList<Task> tasks, Date deadline) {
        if(tasks.isEmpty()||tasks.get(0).getSubtasks().isEmpty()){
            return 0;
        }
        Date startDate = tasks.get(0).getSubtasks().get(0).getStart();

        Calendar start = Calendar.getInstance();
        Calendar dead = Calendar.getInstance();

        start.setTime(startDate);
        dead.setTime(deadline);
        int days = 0;
        while (start.before(dead)) {
            if (Calendar.SATURDAY != start.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != start.get(Calendar.DAY_OF_WEEK)) {
                days++;
            }
            start.add(Calendar.DATE, 1);
        }
        int workHours = calculateProjectDuration(tasks);
        if(days<=0||workHours<=0){
            return 0;
        }
        int dailyWorkHours = workHours / days;

        return dailyWorkHours;
    }

    public int calculateProjectDuration(ArrayList<Task> taskID) {
        int totalDuration = 0;
        for (int i = 0; i < taskID.size(); i++) {
            for (int j = 0; j < taskID.get(i).getSubtasks().size(); j++) {
                totalDuration += taskID.get(i).getSubtasks().get(j).getDuration();
            }
        }
        return totalDuration;
    }

    public String feasible(int dailyHours) {
        if (dailyHours > workDayHours) {
            return "Projektet er ikke gennemførligt";
        } else {
            return "Projektet er gennemførligt";
        }
    }

}