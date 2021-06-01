package com.example.webapp.services;
//@Author Mette Marie H. Winther-Sørensen
import com.example.webapp.models.User;
import com.example.webapp.repository.UserRep;

public class UserService {

    UserRep uRep = new UserRep();

    public boolean checkUser(String mail, String password) {

        User tmp = uRep.checkUser(mail, password);
        System.out.println(tmp != null);
        return tmp != null;
    }
}
