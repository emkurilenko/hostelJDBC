package com.kurilenko.dao.impl;

import com.kurilenko.dao.HostelsDAO;
import com.kurilenko.entity.Hostels;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HostelsDAOImpl implements HostelsDAO {
    private final String SELECT_ALL = "select * from hostels";
    private final String SELECT_ONE = "select * from hostels where id = ?";

    private Connection connection;
    private String SELECT_ONE_BY_NAME = "select * from hostels where name_hostel = ?";

    public HostelsDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
    }

    @Override
    public Long save(Hostels hostels) {
        return null;
    }

    @Override
    public void delete(Long hostels) {

    }

    @Override
    public Hostels getOneById(Long aLong) {
        Hostels hostels = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            hostels = MapperRS.getInstance().rowMapperHostels(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostels;
    }

    @Override
    public List<Hostels> getAll() {
        List<Hostels> hostelsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hostelsList.add(MapperRS.getInstance().rowMapperHostels(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostelsList;
    }

    @Override
    public Hostels getOneByName(String name) {
        Hostels hostels = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            hostels = MapperRS.getInstance().rowMapperHostels(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostels;
    }
}
