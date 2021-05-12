package com.example.webapp.repository;

import com.example.webapp.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserRep {
    private String url;
    private String user;
    private String password;

    public UserRep() {
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

    public User checkUser(String username, String password1) {

        try {
            Connection conn = DriverManager.getConnection(url, user,password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("Email").equals(username)) {
                    if (rs.getString("Password").equals(password1)) {
                        User user = new User(
                                rs.getString(2),
                                rs.getString(3));
                        return user;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with check user");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
