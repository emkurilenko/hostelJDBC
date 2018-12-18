package com.kurilenko.dao.impl;

import com.kurilenko.dao.EmployeesDAO;
import com.kurilenko.entity.Employees;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeesDAOImpl implements EmployeesDAO {
    private final String INSERT_EMPLOYEES = "select * from insert_into_employees(?,?,?)";
    private final String DELETE_BY_ID = "delete from employees where id = ?";

    private Connection connection;
    private MapperRS mapperRS;


    public EmployeesDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Employees employees) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES)) {
            preparedStatement.setLong(1, employees.getId());
            preparedStatement.setLong(2, employees.getFkDepartment());
            preparedStatement.setLong(3, employees.getNumberOfFamilyMembers());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("SAVE EMPLOYEES: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DELETE EMLOYEES: " + e.getMessage());
        }
    }

    @Override
    public Employees getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Employees> getAll() {
        return null;
    }
}
