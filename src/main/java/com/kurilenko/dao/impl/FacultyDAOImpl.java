package com.kurilenko.dao.impl;

import com.kurilenko.dao.FacultyDAO;
import com.kurilenko.entity.Faculty;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO {
    private final String SELECT_ALL = "select * from faculty";
    private final String SELECT_ONE_BY_NAME = "select * from faculty where name_faculty = ?";

    private Connection connection;
    private MapperRS mapperRS;

    public FacultyDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Faculty faculty) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Faculty getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(mapperRS.rowMapperFaculty(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Faculty getFacultyByName(String name) {
        Faculty faculty = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            faculty = mapperRS.rowMapperFaculty(resultSet);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return faculty;
    }
}
