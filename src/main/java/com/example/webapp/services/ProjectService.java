package com.example.webapp.services;

import com.example.webapp.models.Cache;
import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import com.example.webapp.repository.ProjectRep;

import java.util.ArrayList;
import java.util.Date;

public class ProjectService {
    private ProjectRep projectRep;
    private Cache projectCache;
    Calculator projectCalculator;

    public ProjectService(){
        projectCache = new Cache();
        this.projectRep = new ProjectRep();
        projectCalculator = new Calculator();
    }

    public boolean makeProject(String name, String description, String deadline){

        Project newProject = new Project(name, description, deadline);
        if(validateProjectDates(newProject)==true){
            newProject.setNrOfHours(projectCalculator.hoursForProject(newProject));
            newProject.setProjectPrice(projectCalculator.getPriceForProject(newProject.getNrOfHours()));
            projectRep.createProject(newProject);
            updateCache();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateProjectDates(Project newProject) {

        Date deadline = newProject.getDeadline();
        Date dateNow = new Date();

        if(deadline.before(dateNow)){
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

    public ArrayList<Project> getAllProjects() {
        if(projectCache.hasProjects()){
            return projectCache.getAllProjects();
        }
        else{
            projectCache.setProjects(projectRep.getAllProjects());
            return projectCache.getAllProjects();
        }
    }

    public Project getSpecificProject(int projectID) {
        if(projectCache.has(projectID)){
            return projectCache.getProject(projectID);
        }

        return projectRep.getSpecificProject(projectID);
    }

    public void updateCache(){
        projectCache.setProjects(projectRep.getAllProjects());
    }

    public int getDailyWorkHours(ArrayList<Task> tasks, Date deadline) {
        return projectCalculator.dailyWorkHours(tasks, deadline);
    }
}
