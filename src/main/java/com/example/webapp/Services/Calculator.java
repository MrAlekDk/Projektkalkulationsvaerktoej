package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;

import java.time.Duration;
import java.time.LocalDate;

import static java.util.concurrent.TimeUnit.DAYS;

public class Calculator {
        private int hourPrice = 950;
        private int WeeklyWorkHours = 37;
        TaskService tService = new TaskService();

        public int getPriceForProject(int projectHours){
            int projectPrice = hourPrice * projectHours;
            return projectPrice;
        }

        public long timeForTasks(Task task){
            LocalDate start = task.getStartDate();
            LocalDate deadline = task.getTaskDeadline();
            long daysForProject = Duration.between(start, deadline).toDays();
            System.out.println(daysForProject);
            return daysForProject;
        }

        public int dailyWorkHours(Project project){
            int projectDuration = tService.calculateProjectDuration(tService.getAllTasks(1));
            LocalDate startDate = tService.getTaskStartDate(tService.getAllTasks(project.getProjectID()));
            LocalDate deadline = project.getDeadline();
            long daysBetween = Duration.between(startDate.atStartOfDay(), deadline.atStartOfDay()).toDays();

            int dayWorkHours = projectDuration/(int)daysBetween;

            return dayWorkHours;
        }
         //Droppede vi number of participants?
}