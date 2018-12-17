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
    private final String SELECT_ONE_BY_USER_ID = "select hs.id, hs.name_hostel, hs.address, hs.floors " +
            "from hostels as hs inner join user_hostel hostel on hs.id = hostel.fk_hostel_id " +
            "inner join users u on hostel.fk_user_id = u.id where u.id = ?;";
    private String SELECT_ONE_BY_NAME = "select * from hostels where name_hostel = ?";

    private Connection connection;
    private MapperRS mapperRS;

    public HostelsDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
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
    public Hostels getHostelByIdUser(Long id) {
        Hostels hostels = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            hostels = mapperRS.rowMapperHostels(rs);
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hostels;
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
