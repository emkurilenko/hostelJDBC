package com.kurilenko.dao.impl;

import com.kurilenko.dao.DepartmentDAO;
import com.kurilenko.entity.Department;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private final String SELECT_ALL = "select * from department";
    private final String SELECT_ALL_BY_NAME = "select * from department where name_department = ?";

    private Connection connection;
    private MapperRS mapperRS;

    public DepartmentDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Department department) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Department getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                departmentList.add(mapperRS.rowMapperDepartment(resultSet));
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return departmentList;
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            department = mapperRS.rowMapperDepartment(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return department;
    }
}
