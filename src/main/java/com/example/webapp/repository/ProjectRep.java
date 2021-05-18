package com.example.webapp.repository;


import com.example.webapp.models.Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ProjectRep {
    private String url;
    private String user;
    private String password;

    public ProjectRep() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createProject(Project newProject) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Project (ProjectName,ProjectNarrative,Deadline,Price ) VALUES (?,?,?,?)");

            stmt.setString(1, newProject.getName());
            stmt.setString(2, newProject.getDesc());


            java.util.Date utilStartDate1 = newProject.getDeadline();
            java.sql.Date sqlDeadline1 = new java.sql.Date(utilStartDate1.getTime());


            stmt.setDate(3, sqlDeadline1);
            stmt.setDouble(4, newProject.getProjectPrice());
            stmt.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> allProjects = new ArrayList<Project>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Project");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project tmp = new Project(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4)
                );
                allProjects.add(tmp);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allProjects;

    }

    public Project getSpecificProject(int projectID) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Project where ProjectID=?");
            stmt.setInt(1, projectID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project tmpProject = new Project(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4)
                );

                return tmpProject;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
