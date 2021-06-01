package com.example.webapp.repository;
//@Author Alexander J.P.M Sørensen
import com.example.webapp.models.SubTask;
import com.example.webapp.models.Task;

import java.sql.Connection;

public interface dBRepInterface {

    public Connection getConnection(String url,String user,String password);

    public void updateDB(String name, String desc, int deadline, Task task, SubTask subtask);

    public void deleteAll(int projectID);

    public void getAll(int projectID);


}
