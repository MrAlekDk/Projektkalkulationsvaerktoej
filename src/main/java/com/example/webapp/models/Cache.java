package com.example.webapp.models;

import com.example.webapp.Services.ProjectService;
import com.example.webapp.Services.TaskService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Cache {

    Map<Integer, Project> map = new HashMap<Integer, Project>();
    Map<Integer, ArrayList<Task>> test = new HashMap<Integer, ArrayList<Task>>();
    ArrayList<Project> allProjects = new ArrayList<>();

    public Project get(int key){
        return map.get(key);
    }
    public ArrayList<Task> getList (int key){return test.get(key);}

    public Project set(int key, Project value){
        return map.put(key, value);
    }
    public ArrayList<Task> setList(int key, ArrayList<Task> list){return test.put(key, list);}
    public Boolean has(int key){
        return map.containsKey(key);
    }

    public void setProjects(ArrayList<Project> allProjects){
        this.allProjects = allProjects;
    }

    public void delete(int key){
        map.remove(key);
    }

    public void setTTL(int key, long time) {
        TimeUnit timeUnit = null;
        timeUnit.toMinutes(time);
        map.remove(key);
    }

    public boolean hasProjects() {
        if (allProjects.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public Project getProject(int projectID){
        ProjectService projSer = new ProjectService();
        Project tmpProject = projSer.getSpecificProject(projectID);
        return tmpProject;
    }

    public ArrayList<Task> getAllTasks(int projectID){
        TaskService tService = new TaskService();
        ArrayList<Task> allTasks = tService.getAllTasks(projectID);

        com.example.webapp.services.SubTaskService stService = new com.example.webapp.services.SubTaskService();
        stService = new com.example.webapp.services.SubTaskService();

        allTasks = stService.getAllSubTasks(allTasks);

        return allTasks;
    }


    public ArrayList<Project> getAllProjects() {
        return this.allProjects;
    }
}
