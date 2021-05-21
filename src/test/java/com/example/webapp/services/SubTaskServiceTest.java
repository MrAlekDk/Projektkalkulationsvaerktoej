package com.example.webapp.services;

import com.example.webapp.models.SubTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskServiceTest {

    SubTaskService subService = new SubTaskService();

    @Test
    void addTask() {
        // ----------------------------|| Arrange ||---------------------------- //

        //Subtask startdate er sat til en dato der allerede har været der
        String startdate1 = "2020-05-20";
        String deadline1 = "2020-08-27";
        //Subtask hvor startdate er efter deadline
        String startdate2 = "20201-09-03";
        String deadline2 = "2020-08-03";
        //Subtask der er korrekt :-)))
        String startdate3 = "2021-08-03";
        String deadline3 = "2023-05-27";

        // ----------------------------|| Act ||---------------------------- //

        Boolean test1 = subService.addSubtask("name", "desc", 1,23, startdate1,10,deadline1);
        Boolean test2 = subService.addSubtask("name", "desc", 1,420, startdate2,10, deadline2);
        Boolean test3 = subService.addSubtask("name", "desc", 1, 69, startdate3,10, deadline3);

        // ----------------------------|| Assert ||---------------------------- //

        assertEquals(false, test1);
        assertEquals(false, test2);
        assertEquals(true, test3);

    }

    @Test
    void realisticSubtaskTime() {

        // ----------------------------|| Arrange ||---------------------------- //

        //Subtask hvor duration er længere end hvad der er mellem start dato og deadline

        SubTask subtask1 = new SubTask("name", "desc", 1, 23, "2021-08-26", 10,"2021-08-27" );

        //Subtask hvor duration er inden for start dato og deadline

        SubTask subtask2 = new SubTask("name", "desc", 1,23,"2021-08-23", 3,  "2021-08-27");

        Boolean test1 = subService.realisticSubtaskTime(subtask1);
        Boolean test2 = subService.realisticSubtaskTime(subtask2);

        assertEquals(false, test1);
        assertEquals(true, test2);



    }
}