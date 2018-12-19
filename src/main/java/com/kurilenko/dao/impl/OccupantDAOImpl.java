package com.kurilenko.dao.impl;

import com.kurilenko.dao.OccupantDAO;
import com.kurilenko.entity.Occupant;
import com.kurilenko.entity.OccupantInfo;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OccupantDAOImpl implements OccupantDAO {
    private final String SELECT_ALL_INFO_BY_ID_HOSTEL = "select * from get_occupant_info_property_all(?)";
    private final String SELECT_ALL_INFO_BY_ID_HOSTEL_ADMIN = "select * from get_occupant_info_property_all_admin()";
    private final String INSERT_NEW_OCCUPANT = "select *from insert_into_occupant(?,?,?,?,?,?,?)";
    private final String DELETE_BY_ID = "delete from occupant where id = ?";
    private final String SELECT_OCCUPANT_BY_ID = "select * from occupant where id = ?";
    private Connection connection;
    private MapperRS mapperRS;

    public OccupantDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Occupant occupant) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_OCCUPANT)) {
            preparedStatement.setString(1, occupant.getFirstName());
            preparedStatement.setString(2, occupant.getMiddleName());
            preparedStatement.setString(3, occupant.getLastName());
            preparedStatement.setDate(4, java.sql.Date.valueOf(occupant.getDateOfBirth()));
            preparedStatement.setString(5, occupant.getTelephone());
            preparedStatement.setString(6, occupant.getGender());
            preparedStatement.setString(7, occupant.getOccupantType());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("SAVE OCCUPANT: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DELETE OCCUPANT: " + e.getMessage());
        }
    }

    @Override
    public Occupant getOneById(Long aLong) {
        Occupant occupant = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OCCUPANT_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            occupant = mapperRS.rowMapperOccupant(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("GET OCCUPANT: " + e.getMessage());
        }
        return occupant;
    }

    @Override
    public List<Occupant> getAll() {
        return null;
    }

    @Override
    public List<OccupantInfo> getAllInfoByIdHostel(Long id) {
        List<OccupantInfo> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INFO_BY_ID_HOSTEL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(mapperRS.rowMapperOccupantInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<OccupantInfo> getAllInfoByIdHostelAdmin() {
        List<OccupantInfo> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INFO_BY_ID_HOSTEL_ADMIN)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(mapperRS.rowMapperOccupantInfo(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


}
