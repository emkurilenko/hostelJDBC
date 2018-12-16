package com.kurilenko.dao;

import com.kurilenko.entity.Hostels;
import com.kurilenko.entity.User;
import com.kurilenko.entity.UserInfo;
import com.kurilenko.exception.UserNotFoundException;

import java.util.List;

public interface UserDAO extends BaseDAO<User, Long> {
    User getUser(User user) throws UserNotFoundException;
    Long createUser(User user, Hostels hostels);
    List<UserInfo> getAllUserInfo();

    Long update(User user, Hostels hostel);
}
