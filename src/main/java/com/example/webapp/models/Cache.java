package com.example.webapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cache {

    Map<Integer, Project> mapOfProjects = new HashMap<Integer, Project>();

    public Boolean has(int projectID){
        return mapOfProjects.containsKey(projectID);
    }

    public void setProjects(ArrayList<Project> allProjects){
        for (int i = 0; i < allProjects.size(); i++) {
            mapOfProjects.put(allProjects.get(i).getProjectID(),allProjects.get(i));
        }
    }

    public void delete(int key){
        mapOfProjects.remove(key);
    }

    public boolean hasProjects() {
        if (mapOfProjects.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }


    public ArrayList<Project> getAllProjects() {
        return new ArrayList<Project>(mapOfProjects.values());
    }

    public Project getProject(int projectID) {
        return mapOfProjects.get(projectID);
    }

    public void removeProject(int projectID) {
        mapOfProjects.remove(projectID);
    }
}
