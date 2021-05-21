package com.example.webapp.services;

import com.example.webapp.models.Project;
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator cal = new Calculator();
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
        Task test2 = new Task(2, "testTask2", "testDescription2", 2, new Date(2021,6,25), 10, new Date(2021,6,26));
        Task test3 = new Task(4, "testTask4", "testDescription4", 3, new Date(2021,6,28), 10, new Date(2021,6,30));
        project3.add(test3);

        SubTask subTask1 =  new SubTask("name", "desc",  1 , 1, "2021-06-21",10, "2021-6-22");
        SubTask subTask2 =  new SubTask("name", "desc",  1 , 1, "2021-06-17",20, "2021-6-21");
        SubTask subTask3 =  new SubTask("name", "desc",  1 , 1, "2021-06-21",10, "2021-6-22");

        test1.addSubtask(subTask1);
        project1.add(test1);
        test2.addSubtask(subTask2);
        test2.addSubtask(subTask3);
        project2.add(test2);
        // ----------------------------|| Act ||---------------------------- //
       int dailyTest1 = cal.dailyWorkHours(project1, testProject.getDeadline());
       int dailyTest2 = cal.dailyWorkHours(project2, testProject.getDeadline());
       //int dailyTest3 = cal.dailyWorkHours(project3, project3.get(2).getTaskDeadline());
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals(5, dailyTest1);
        assertEquals(7, dailyTest2);
    }

    @Test
    void calculateProjectDuration() {
        // ----------------------------|| Arrange ||---------------------------- //
        // project list of tasks
        ArrayList<Task> project1 = new ArrayList<>();
        ArrayList<Task> project2 = new ArrayList<>();
        // tasks in each project
        Task test1 = new Task(1, "testTask1", "testDescription1", 1, new Date(2021,6,21), 6, new Date(2021,6,22));
        Task test2 = new Task(2, "testTask2", "testDescription2", 2, new Date(2021,6,25), 10, new Date(2021,6,26));

        SubTask subTask1 =  new SubTask("name", "desc",  1 , 1, "2021-06-21",220, "2021-6-22");
        SubTask subTask2 =  new SubTask("name", "desc",  1 , 1, "2021-06-17",200, "2021-6-21");
        SubTask subTask3 =  new SubTask("name", "desc",  1 , 1, "2021-06-21",34, "2021-6-22");
        SubTask subTask4 =  new SubTask("name", "desc",  1 , 1, "2021-06-21",35, "2021-6-22");
        test1.addSubtask(subTask1);
        test1.addSubtask(subTask2);
        project1.add(test1);
        test2.addSubtask(subTask3);
        test2.addSubtask(subTask4);
        project2.add(test2);
        // ----------------------------|| Act ||---------------------------- //
        int duration1 = cal.calculateProjectDuration(project1);
        int duration2 = cal.calculateProjectDuration(project2);
        // ----------------------------|| Assert ||---------------------------- //
        assertEquals(420, duration1);
        assertEquals(69, duration2);
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
        assertEquals("Projektet er feasible", feasible1);
        assertEquals("Projektet er feasible", feasible2);
        assertEquals("Projektet er ikke feasible", feasible3);
    }
}