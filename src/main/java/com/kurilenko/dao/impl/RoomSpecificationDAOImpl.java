package com.kurilenko.dao.impl;

import com.kurilenko.dao.RoomSpecificationDAO;
import com.kurilenko.entity.RoomSpecification;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomSpecificationDAOImpl implements RoomSpecificationDAO {
    private final String SELECT_ALL = "select * from room_specification";
    private final String SELECT_ONE = "select * from room_specification where id = ?";
    private final String SELECT_ONE_BY_TYPE = "select * from room_specification where type_of_room = ?";
    private Connection connection;
    private MapperRS mapperRS;

    public RoomSpecificationDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(RoomSpecification roomSpecification) {

        return null;
    }

    @Override
    public void delete(Long room) {

    }

    @Override
    public RoomSpecification getOneById(Long aLong) {
        RoomSpecification roomSpecification = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            roomSpecification = mapperRS.rowMapperRoomSpecification(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomSpecification;
    }

    @Override
    public List<RoomSpecification> getAll() {
        List<RoomSpecification> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(mapperRS.rowMapperRoomSpecification(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public RoomSpecification getOneByType(String type) {
        RoomSpecification roomSpecification = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_TYPE)) {
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            roomSpecification = mapperRS.rowMapperRoomSpecification(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomSpecification;
    }
}
