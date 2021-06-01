package com.example.webapp.caches;
// @Author Alexander J.P.M Sørensen

import com.example.webapp.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskCache {

    Map<Integer, ArrayList<Task>> mapOfTask = new HashMap<Integer, ArrayList<Task>>();

    public TaskCache(){
    }

    public boolean hasTasks(int projectID) {
        if (mapOfTask.containsKey(projectID)) {
            return true;
        } else {
            return false;
        }
    }

    public void setMapOfTask(ArrayList<Task> allTasks){
        for (int i = 0; i < allTasks.size(); i++) {
            mapOfTask.put(allTasks.get(i).getProjectID(),allTasks);
        }
    }

    public ArrayList<Task> getAllTask(int projectID){
        return new ArrayList<Task>(mapOfTask.get(projectID));
    }

    public void removeTaskList(int projectID) {
        mapOfTask.remove(projectID);
    }
}
