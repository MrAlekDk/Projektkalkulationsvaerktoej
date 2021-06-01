package com.example.webapp.services;
//@Author Markus Nørtrander Lauge Jakobsen
//@Author Mette Marie H. Winther-Sørensen
import com.example.webapp.models.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    ProjectService proService = new ProjectService();

    @Test
    void makeProject(){
        //vær OBS på at når man kører denne test, så opretter den et projekt i DB
        // ----------------------------|| Arrange ||---------------------------- //

        String deadline1 = "2021-05-19";
        String deadline2 = "2021-05-25";

        // ----------------------------|| Act ||---------------------------- //

        Boolean test1 = proService.makeProject("name","descp", deadline1);
        Boolean test2 = proService.makeProject("name","descp", deadline2);

        // ----------------------------|| Assert ||---------------------------- //

        assertEquals(false, test1);
        assertEquals(true, test2);


    }

    @Test
    void updateCache() {
        // ----------------------------|| Arrange ||---------------------------- //

        Project project1 = new Project("name", "descp", "2021-04-27");
        Project project2 = new Project("name", "descp", "2021-07-13");
        Project project3 = new Project("name", "descp", "2021-06-07");

        // ----------------------------|| Act ||---------------------------- //


        // ----------------------------|| Assert ||---------------------------- //
    }
}