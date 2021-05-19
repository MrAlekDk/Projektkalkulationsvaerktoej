package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;

import java.time.*;
import java.util.ArrayList;
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

        public int dailyWorkHours(Project project){
            Date startDate = tService.orderTaskStartDate(project.getTasks());
            Date deadline = project.getDeadline();

           LocalDate start = Instant.ofEpochMilli(startDate.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

           LocalDate dead = Instant.ofEpochMilli(deadline.getTime())
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
            Period period = Period.between(start, dead);

            int days = period.getDays();
            int workHours = tService.calculateProjectDuration(project.getTasks());

           int dailyWorkHours = days/workHours;

            return dailyWorkHours;
        }

}