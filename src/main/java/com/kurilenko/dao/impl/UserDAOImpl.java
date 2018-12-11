package com.kurilenko.dao.impl;

import com.kurilenko.dao.UserDAO;
import com.kurilenko.entity.User;
import com.kurilenko.entity.enums.UserRole;
import com.kurilenko.exception.UserNotFoundException;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final String USER_INSERT = "insert into users(id,username,password,user_role) values (DEFAULT,?,?,?) returning id";
    private final String SELECT_USERS = "select * from users";
    private final Connection connection;

    public UserDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
    }

    @Override
    public Long save(User user) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(USER_INSERT)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, MD5.getHash(user.getPassword()));
            preparedStatement.setString(3, user.getUserRole().name());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USERS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(
                        new User(rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                UserRole.valueOf(rs.getString(4))));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    @Override
    public User getUser(User user) throws UserNotFoundException {
        return getAll().stream().filter(u ->
                u.getPassword().equals(user.getPassword()) && u.getUsername().equals(user.getUsername())).findFirst().
                orElseThrow(() -> new UserNotFoundException(user.getUsername()));
    }
}
