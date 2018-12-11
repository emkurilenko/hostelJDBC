package com.kurilenko.dao;

import com.kurilenko.entity.User;
import com.kurilenko.exception.UserNotFoundException;

public interface UserDAO extends BaseDAO<User, Long> {
    User getUser(User user) throws UserNotFoundException;
}
