package com.example.webapp.services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import com.example.webapp.models.Worker;
import com.example.webapp.repository.ProjectRep;
import com.example.webapp.repository.TaskRep;

import java.util.ArrayList;
import java.util.Date;

public class ProjectService {
    private ProjectRep projectRep;
    private TaskRep taskRep;

    public void makeProject(Project newProject){
        double projectPrice=calculateProjectPrice(newProject);
        newProject.setProjectPrice(projectPrice);
        projectRep.createProject(newProject);

    }

    public void makeTask(String title, String desc, int worker_ID, Date startDate, int nrOfHours, Date taskDeadline){

    }

    public int calculateProjectPrice(Project projectToCalculate){
        int hours = taskRep.calculateTime(project_ID);


        // Ved ikke helt hvordan vi finder ud af hvor mange der er af hver position, måske fra workerRep?
        int nrOfParticipants = project.getNrOfParticipants();
        int salary = 0;
        int projectPrice = salary * hours * nrOfParticipants;
        // Når vi har de forskellige posititions med kan vi bruge projectPrice for hver position og pluse det sammen til projektets totale pris.
        return projectPrice;
    }

    public int calculateTimeForProject(Project project, int project_ID){
        int hours = taskRep.calculateTime(project_ID);
        int nrOfParticipants = project.getNrOfParticipants();
        //Måske et lille regnestykke her istedet for bare at vise det vil tage 200 timer fx. så også vise dage måske
        return 1;
    }

}
