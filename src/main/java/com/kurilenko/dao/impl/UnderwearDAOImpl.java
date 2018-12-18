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
    private Connection connection;
    private MapperRS mapperRS;

    public UnderwearDAOImpl(){
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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(mapperRS.rowMapperUnderwear(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
