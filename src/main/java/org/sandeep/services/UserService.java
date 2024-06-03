package org.sandeep.services;

import org.mindrot.jbcrypt.BCrypt;
import org.sandeep.dao.UserDAO;
import org.sandeep.models.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser;


    public boolean login(String username, String password) {
        User user =  userDAO.getUserByUsername(username);
        if(user != null && BCrypt.checkpw(password,user.getPassword())) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public boolean adminLogin(String username, String password) {
        User user =  userDAO.getUserByUsername(username);
        if(user != null && user.getRole().equals("admin") && BCrypt.checkpw(password,user.getPassword())) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void signup(User user) {
        String hassedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hassedPassword);
        userDAO.signup(user);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
