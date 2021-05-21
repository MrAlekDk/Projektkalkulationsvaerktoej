package com.example.webapp.services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    TaskService tService = new TaskService();
    @Test
    void addTask() {

        // ----------------------------|| Arrange ||---------------------------- //

        //Task startdate er sat til en dato der allerede har været der
        String startdate1 = "2020-05-20";
        String deadline1 = "2020-08-27";
        //Task hvor startdate er efter deadline
        String startdate2 = "20201-09-03";
        String deadline2 = "2020-08-03";
        //Task der er korrekt :-)))
        String startdate3 = "2021-08-03";
        String deadline3 = "2023-05-27";

        // ----------------------------|| Act ||---------------------------- //

        Boolean test1 = tService.addTask("name", "desc", 1,startdate1,10,deadline2);
        Boolean test2 = tService.addTask("name", "desc", 1, startdate2,10, deadline2);
        Boolean test3 = tService.addTask("name", "desc", 1, startdate3,10, deadline3);

        // ----------------------------|| Assert ||---------------------------- //

        assertEquals(false, test1);
        assertEquals(false, test2);
        assertEquals(true, test3);


    }
}