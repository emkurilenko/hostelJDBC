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
    private String SELECT_ALL_INFO_BY_ID_HOSTEL = "select * from get_occupant_info_property_all(?)";
    private String SELECT_ALL_INFO_BY_ID_HOSTEL_ADMIN = "select * from get_occupant_info_property_all_admin()";

    private Connection connection;
    private MapperRS mapperRS;

    public OccupantDAOImpl(){
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Occupant occupant) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Occupant getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Occupant> getAll() {
        return null;
    }

    @Override
    public List<OccupantInfo> getAllInfoByIdHostel(Long id) {
        List<OccupantInfo> list = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INFO_BY_ID_HOSTEL)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(mapperRS.rowMapperOccupantInfo(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<OccupantInfo> getAllInfoByIdHostelAdmin() {
        List<OccupantInfo> list = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INFO_BY_ID_HOSTEL_ADMIN)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(mapperRS.rowMapperOccupantInfo(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }


}
