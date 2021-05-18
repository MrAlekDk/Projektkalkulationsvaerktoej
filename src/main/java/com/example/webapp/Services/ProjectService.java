package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.repository.ProjectRep;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ProjectService {
    private ProjectRep projectRep;

    public ProjectService(){
        this.projectRep = new ProjectRep();
    }

    public boolean makeProject(Project newProject){
        newProject.setProjectPrice(500.00);

        if(validateProjectDates(newProject)==true){
            projectRep.createProject(newProject);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateProjectDates(Project newProject) {

        LocalDate deadline = newProject.getDeadline();
        LocalDate dateNow = LocalDate.now();

        if(deadline.isBefore(dateNow)){
            return false;
        }
        else{
            return true;
        }
    }

    public double calculateProjectPrice(Project projectToCalculate){

        return 500.00;
        //int hours = taskRep.calculateTime(project_ID);


        // Ved ikke helt hvordan vi finder ud af hvor mange der er af hver position, måske fra workerRep?
        //int projectPrice = salary * hours * nrOfParticipants;
        // Når vi har de forskellige posititions med kan vi bruge projectPrice for hver position og pluse det sammen til projektets totale pris.
        //return projectPrice;
    }

    public ArrayList<Project> getAllProjectS() {
        return projectRep.getAllProjects();
    }

    public Project getSpecificProject(int projectID) {
        return projectRep.getSpecificProject(projectID);
    }
}
