package com.example.webapp.repository;

import com.example.webapp.models.Task;
import com.example.webapp.models.Worker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ProjectRep {

    private String url;
    private String user;
    private String password;

    public ProjectRep(){
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void createProject(String projectTitle, String desc, Date deadline, int worker_ID, int nrOfParticipants, int nrOfHours, int task_ID){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Project (Title,Desc,Deadline,Worker_ID, NrOfParticipants, NrOfHours, Task_ID) VALUES (?,?,?,?,?,?,?,?)");

            pstmt.setString(1, projectTitle);
            pstmt.setString(2, desc);
            pstmt.setDate(3, (java.sql.Date) deadline);
            pstmt.setInt(4, worker_ID);
            pstmt.setInt(5, nrOfParticipants);
            pstmt.setInt(6, nrOfHours);
            pstmt.setInt(7, task_ID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
