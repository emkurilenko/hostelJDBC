package com.kurilenko.dao.impl;

import com.kurilenko.dao.UserDAO;
import com.kurilenko.entity.Hostels;
import com.kurilenko.entity.User;
import com.kurilenko.entity.UserInfo;
import com.kurilenko.entity.enums.UserRole;
import com.kurilenko.exception.UserNotFoundException;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MD5;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final String USER_INSERT = "insert into users(id,username,password,user_role) values (DEFAULT,?,?,?) returning id";
    private final String USER_CREATE = "select * from create_new_user(?,?,?,?)";
    private final String GET_ALL_USER_INFO = "select * from get_user_info_all()";
    private final String SELECT_USERS = "select * from users";
    private final String DELETE_USER = "delete from users where id = ?";
    private final String UPDATE_USER_INFO = "select * from update_user_info(?,?,?,?,?)";


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
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    @Override
    public Long createUser(User user, Hostels hostels) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(USER_CREATE)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserRole().name());
            preparedStatement.setString(4, hostels.getNameHostel());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_INFO)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userInfoList.add(MapperRS.getInstance().rowMapperUserInfo(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userInfoList;
    }

    @Override
    public Long update(User user, Hostels hostel) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INFO)) {
            System.out.println(user);
            System.out.println(hostel);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3, MD5.getHash(user.getPassword()));
            preparedStatement.setString(4, user.getUserRole().name());
            preparedStatement.setString(5, hostel.getNameHostel());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            id = rs.getLong(1);
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
}
