package org.sandeep.services;

import org.sandeep.dao.UserDAO;
import org.sandeep.models.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser;


    public boolean login(String username, String password) {
        loggedInUser = userDAO.login(username, password);
        return loggedInUser != null;
    }

    public boolean adminLogin(String username, String password) {
        return userDAO.adminLogin(username, password);
    }

    public void signup(User user) {
        userDAO.signup(user);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
