package com.example.webapp.services;

import com.example.webapp.models.User;
import com.example.webapp.repository.UserRep;

public class UserService {

    UserRep uRep = new UserRep();

    public boolean checkUser(String mail, String password) {

        User tmp = uRep.checkUser(mail, password);

        if (tmp != null) {
            return true;
        } else {
            return false;
        }
    }
}
