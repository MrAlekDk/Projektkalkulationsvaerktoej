package com.example.webapp.services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator cal = new Calculator();
    TaskService tService = new TaskService();
    @Test
    void getPriceForProject() {
        // ----------------------------|| Arrange ||---------------------------- //
        int projectTimeA = 10;
        int projectTimeB = 712;
        int projectTimeC = 400;
        // ----------------------------|| Act ||---------------------------- //
        int priceA = cal.getPriceForProject(projectTimeA);
        int priceB = cal.getPriceForProject(projectTimeB);
        int priceC = cal.getPriceForProject(projectTimeC);
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals(9500, priceA);
        assertEquals(676400, priceB);
        assertEquals(380000, priceC);
    }
/*
    @Test
    void hoursForProject() {
        // ----------------------------|| Arrange ||---------------------------- //
       // project list of tasks
        ArrayList<Task> project1 = new ArrayList<>();
        ArrayList<Task> project2 = new ArrayList<>();
        ArrayList<Task> project3 = new ArrayList<>();
        // tasks in each project
        Task test1 = new Task(1, "testTask1", "testDescription1", 1, new Date(2021,6,21), 6, new Date(2021,6,22));
        project1.add(test1);
        Task test2 = new Task(2, "testTask2", "testDescription2", 2, new Date(2021,6,25), 10, new Date(2021,6,26));
        Task test3 = new Task(3, "testTask3", "testDescription3", 2, new Date(2021,6,27), 10, new Date(2021,6,28));
        project2.add(test2);
        project2.add(test3);
        Task test4 = new Task(4, "testTask4", "testDescription4", 3, new Date(2021,6,28), 10, new Date(2021,6,30));
        Task test5 = new Task(5, "testTask5", "testDescription5", 3, new Date(2021,7,1), 5, new Date(2021,7,5));
        Task test6 = new Task(6, "testTask6", "testDescription6", 3, new Date(2021,7,6), 15, new Date(2021,7,7));
        project3.add(test4);
        project3.add(test5);
        project3.add(test6);
        // ----------------------------|| Act ||---------------------------- //
        int hours1 = cal.hoursForProject(project1);
        int hours2 = cal.hoursForProject(project2);
        int hours3 = cal.hoursForProject(project3);
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals(8, hours1);
        assertEquals(24, hours2);
        assertEquals(80, hours3);
    }

 */

    @Test
    void dailyWorkHours() {
        // ----------------------------|| Arrange ||---------------------------- //
        // project list of tasks
        ArrayList<Task> project1 = new ArrayList<>();
        ArrayList<Task> project2 = new ArrayList<>();
        ArrayList<Task> project3 = new ArrayList<>();
        Project testProject = new Project("name", "desc", "2021-06-23");
        // tasks in each project
        Task test1 = new Task(1, "testTask1", "testDescription1", 1, new Date(2021,6,21), 6, new Date(2021,6,22));
        project1.add(test1);
        Task test2 = new Task(2, "testTask2", "testDescription2", 2, new Date(2021,6,25), 10, new Date(2021,6,26));
        project2.add(test2);
        Task test3 = new Task(4, "testTask4", "testDescription4", 3, new Date(2021,6,28), 10, new Date(2021,6,30));
        project3.add(test3);

        // ----------------------------|| Act ||---------------------------- //
       int dailyTest1 = cal.dailyWorkHours(project1, testProject.getDeadline());
       int dailyTest2 = cal.dailyWorkHours(project2, testProject.getDeadline());
       int dailyTest3 = cal.dailyWorkHours(project3, project3.get(2).getTaskDeadline());
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals(0, dailyTest1);
        assertEquals(24, dailyTest2);
        assertEquals(dailyTest3, 212);
    }

    @Test
    void calculateProjectDuration() {
        // ----------------------------|| Arrange ||---------------------------- //
        // ----------------------------|| Act ||---------------------------- //
        // ----------------------------|| Assert ||---------------------------- //
    }

    @Test
    void feasible() {
        // ----------------------------|| Arrange ||---------------------------- //
        int dailyHours1 = 8;
        int dailyHours2 = 0;
        //Not feasible
        int dailyHours3 = 9;
        // ----------------------------|| Act ||---------------------------- //
        String feasible1 = cal.feasible(dailyHours1);
        String feasible2 = cal.feasible(dailyHours2);
        String feasible3 = cal.feasible(dailyHours3);
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals("This project is feasible", feasible1);
        assertEquals("This project is feasible", feasible2);
        assertEquals("This project is not feasible", feasible3);
    }
}