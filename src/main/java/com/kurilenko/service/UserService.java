package com.kurilenko.service;

import com.kurilenko.dao.UserDAO;
import com.kurilenko.dao.impl.UserDAOImpl;
import com.kurilenko.entity.User;
import com.kurilenko.exception.UserNotFoundException;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    public boolean saveUser(User user) {
        if (userDAO.save(user) != null)
            return true;
        return false;
    }

    public User existUser(User user) {
        try {
            return userDAO.getUser(user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
