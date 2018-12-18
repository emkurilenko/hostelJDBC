package com.kurilenko.dao.impl;

import com.kurilenko.dao.GroupStudentsDAO;
import com.kurilenko.entity.GroupStudents;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupStudentsDAOImpl implements GroupStudentsDAO {
    private final String SELECT_GROUP_NAME = "select * from group_students where name_group = ?";
    private final String SELECT_ALL = "select * from group_students";
    private final String INSERT_INTO = "insert into group_students(id,name_group, fk_specialty) values (default, ?,?) returning id";

    private Connection connection;
    private MapperRS mapperRS;

    public GroupStudentsDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(GroupStudents groupStudents) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, groupStudents.getNameGroup());
            preparedStatement.setLong(2, groupStudents.getFkSpecialty());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SAVE GROUP:" + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GroupStudents getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<GroupStudents> getAll() {
        List<GroupStudents> groupStudents = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupStudents.add(mapperRS.rowMapperGroupStudents(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groupStudents;
    }

    @Override
    public GroupStudents getOneByName(String name) {
        GroupStudents groupStudents = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupStudents = mapperRS.rowMapperGroupStudents(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groupStudents;
    }
}
