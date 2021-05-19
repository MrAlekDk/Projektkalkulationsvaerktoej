package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.time.Instant.ofEpochMilli;

public class Calculator {
        private int hourPrice = 950;
        private int WeeklyWorkHours = 37;
        TaskService tService = new TaskService();

        public int getPriceForProject(int projectHours){
            int projectPrice = hourPrice * projectHours;
            return projectPrice;
        }

        public int hoursForProject(Project project){
            Date startDate = tService.orderTaskStartDate(project.getTasks());
            Date deadline = project.getDeadline();

            LocalDate start = Instant.ofEpochMilli(startDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate dead = Instant.ofEpochMilli(deadline.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            Period period = Period.between(start, dead);
             int days =period.getDays();
             return days * 8;
        }

        public int dailyWorkHours(Project project){
            Date startDate = tService.orderTaskStartDate(project.getTasks());
            Date deadline = project.getDeadline();

            Calendar start = Calendar.getInstance();
            Calendar dead = Calendar.getInstance();

            start.setTime(startDate);
            dead.setTime(deadline);
            int days = 0;

            while (start.before(dead)){
                if (Calendar.SATURDAY != start.get(Calendar.DAY_OF_WEEK) && Calendar.SUNDAY != start.get(Calendar.DAY_OF_WEEK)){
                    days++;
                }
            }

            int workHours = tService.calculateProjectDuration(project.getTasks());

           int dailyWorkHours = days/workHours;

            return dailyWorkHours;
        }

}