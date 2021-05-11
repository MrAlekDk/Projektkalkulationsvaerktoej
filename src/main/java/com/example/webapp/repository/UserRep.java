package com.example.webapp.repository;

import java.io.FileInputStream;
import java.io.IOException;
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
}
