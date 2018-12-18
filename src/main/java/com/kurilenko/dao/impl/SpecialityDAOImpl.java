package com.kurilenko.dao.impl;

import com.kurilenko.dao.SpecialityDAO;
import com.kurilenko.entity.Specialty;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDAOImpl implements SpecialityDAO {

    private final String SELECT_ALL = "select * from specialty";
    private final String SELECT_ONE_BY_NAME = "select * from specialty where name_specialty = ?";
    private final String INSERT_SPECIALITY = "insert into specialty(id, name_specialty,id_department) values (default, ?,?) returning id";

    private Connection connection;
    private MapperRS mapperRS;

    public SpecialityDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Specialty specialty) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPECIALITY)) {
            preparedStatement.setString(1, specialty.getNameSpecialty());
            preparedStatement.setLong(2, specialty.getIdDepartment());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        }catch (SQLException e){
            System.out.println("SAVE Specialty: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Specialty getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialties.add(mapperRS.rowMapperSpecialty(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return specialties;
    }

    @Override
    public Specialty getOneByName(String value) {
        Specialty specialty = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_NAME)) {
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            specialty = mapperRS.rowMapperSpecialty(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return specialty;
    }
}
