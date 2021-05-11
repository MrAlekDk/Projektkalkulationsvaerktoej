package com.example.webapp.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class TaskRep {
    private String url;
    private String user;
    private String password;

    public TaskRep(){
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

    public void createTask(int task_ID, String title, String desc, int worker_ID, Date startDate, int nrOfHours, Date taskDeadline){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Project (Task_ID, Title, Desc, Worker_ID, StartDate, NrOfHours, TaskDeadline) VALUES (?,?,?,?,?,?,?)");

            pstmt.setInt(1, task_ID);
            pstmt.setString(2, title);
            pstmt.setString(3, desc);
            pstmt.setInt(4, worker_ID);
            pstmt.setDate(5, (java.sql.Date) startDate);
            pstmt.setInt(6, nrOfHours);
            pstmt.setDate(7,(java.sql.Date) taskDeadline);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
