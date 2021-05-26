package com.example.webapp.services;

import com.example.webapp.models.Task;

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

        int days = 0;
        int workHours=0;
        try{
            Date startDate = tasks.get(0).getSubtasks().get(0).getStart();
            Calendar start = Calendar.getInstance();
            Calendar dead = Calendar.getInstance();

            start.setTime(startDate);
            dead.setTime(deadline);

            while (start.before(dead)) {
                if (Calendar.SATURDAY != start.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != start.get(Calendar.DAY_OF_WEEK)) {
                    days++;
                }
                start.add(Calendar.DATE, 1);
            }
        }catch(Exception e){
        }
        workHours = calculateProjectDuration(tasks);
        int dailyWorkHours =0;
        try{
            dailyWorkHours = workHours / days;
        }
        catch(ArithmeticException e){
        }

        return dailyWorkHours;
    }

    public int calculateProjectDuration(ArrayList<Task> listOFTasks) {

        int totalDuration = 0;
        try{
            for (int i = 0; i < listOFTasks.size(); i++) {
                for (int j = 0; j < listOFTasks.get(i).getSubtasks().size(); j++) {
                    totalDuration += listOFTasks.get(i).getSubtasks().get(j).getDuration();
                }
            }
        }catch(NullPointerException e){
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