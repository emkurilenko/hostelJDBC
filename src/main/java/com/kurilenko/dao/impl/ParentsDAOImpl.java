package com.kurilenko.dao.impl;

import com.kurilenko.dao.ParentsDAO;
import com.kurilenko.entity.Parents;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ParentsDAOImpl implements ParentsDAO {
    private final String INSERT_INTO_PARENTS = "select * from insert_into_parents(?,?,?,?,?)";
    private Connection connection;
    private MapperRS mapperRS;

    public ParentsDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Parents parents) {
        return null;
    }

    public Long saveWithIdStudent(Long id, Parents parents) {
        Long _id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PARENTS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, parents.getFirstName());
            preparedStatement.setString(3, parents.getMiddleName());
            preparedStatement.setString(4, parents.getLastName());
            preparedStatement.setString(5, parents.getStatus());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            _id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("SAVE WITH ID STUDENT: " + e.getMessage());
        }
        return _id;
    }

    @Override
    public void delete(Long parents) {

    }

    @Override
    public Parents getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Parents> getAll() {
        return null;
    }
}
