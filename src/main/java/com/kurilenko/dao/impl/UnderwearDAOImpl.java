package com.kurilenko.dao.impl;

import com.kurilenko.dao.UnderwearDAO;
import com.kurilenko.entity.Underwear;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnderwearDAOImpl implements UnderwearDAO {
    private final String SELECT_ALL = "select * from underwear";
    private final String INSERT_INTO_ISSUED_LINEN = "insert into issued_linen values(?,?) return";
    private final String SELECT_ONE_BY_NAME = "select * from underwear where name_underwerar = ?";
    private Connection connection;
    private MapperRS mapperRS;

    public UnderwearDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Underwear underwear) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Underwear getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Underwear> getAll() {
        List<Underwear> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(mapperRS.rowMapperUnderwear(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Long saveUnderwearWithIdContract(String nameUnderwear, Long idContract) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ISSUED_LINEN)) {
            preparedStatement.setLong(2, getByNameUnderwear(nameUnderwear).getId());
            preparedStatement.setLong(1, idContract);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("saveUnderwearWithIdContract: " + e.getMessage());
        }
        return id;
    }

    @Override
    public Underwear getByNameUnderwear(String name) {
        Underwear underwear = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            underwear = mapperRS.rowMapperUnderwear(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("getByNameUnderwear: " + e.getMessage());
        }
        return underwear;
    }
}
