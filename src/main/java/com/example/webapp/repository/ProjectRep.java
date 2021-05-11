package com.example.webapp.repository;


import com.example.webapp.models.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class ProjectRep{
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

    public void createProject(Project newProject){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Project (ProjectName,ProjectNarrative,Deadline,Price ) VALUES (?,?,?,?)");

            stmt.setString(1, newProject.getName());
            stmt.setString(2, newProject.getDesc());


            java.util.Date utilStartDate = newProject.getDeadline();
            java.sql.Date sqlDeadline = new java.sql.Date(utilStartDate.getTime());

            stmt.setDate(3, sqlDeadline);
            stmt.setDouble(4, newProject.getProjectPrice());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
