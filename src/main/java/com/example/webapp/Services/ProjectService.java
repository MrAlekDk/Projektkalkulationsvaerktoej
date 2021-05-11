package com.example.webapp.Services;

import com.example.webapp.models.Project;
import com.example.webapp.models.Task;
import com.example.webapp.models.Worker;
import com.example.webapp.repository.ProjectRep;

import java.util.ArrayList;
import java.util.Date;

public class ProjectService {
    private ProjectRep projectRep;


    public void makeProject(int project_ID, String title, String desc, Date deadline, int worker_ID, int nrOfParticipants, int nrOfHours, int task_ID){
        projectRep.createProject(title, desc, deadline, worker_ID, nrOfParticipants, nrOfHours, task_ID);
         //osv...
    };

    public void makeTask(String title, String desc, int worker_ID, Date startDate, int nrOfHours, Date taskDeadline){

    }

    public int calculateProjectPrice(Project project){ return 1;}

    public int calculateTimeForProject(Project project){ return 1;}

}
