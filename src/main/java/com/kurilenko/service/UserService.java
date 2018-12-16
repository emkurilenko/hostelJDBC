package com.kurilenko.service;

import com.kurilenko.dao.UserDAO;
import com.kurilenko.dao.impl.UserDAOImpl;
import com.kurilenko.entity.Hostels;
import com.kurilenko.entity.User;
import com.kurilenko.entity.UserInfo;
import com.kurilenko.entity.UserProperty;
import com.kurilenko.exception.UserNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

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

    public Long createUser(User user, Hostels hostel){
        return userDAO.createUser(user, hostel);
    }

    public List<UserInfo> getAllUserInfo(){
        return userDAO.getAllUserInfo();
    }

    public ObservableList<UserProperty> getAllUserProperty(){
        ObservableList<UserProperty> dummyData = FXCollections.observableArrayList();
        dummyData.addAll(getAllUserInfo().stream().map(userInfo -> createUserProperty(userInfo)).collect(Collectors.toList()));
        return dummyData;
    }

    private UserProperty createUserProperty(UserInfo userInfo){
        return new UserProperty(userInfo.getId(),
                userInfo.getUsername(),
                userInfo.getHostel(),
                userInfo.getRole().name());
    }

    public void delete(Long id){
        userDAO.delete(id);
    }

    public Long updateUser(User user, Hostels hostel){
        return userDAO.update(user, hostel);
    }
}
