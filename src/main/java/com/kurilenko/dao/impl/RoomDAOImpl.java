package com.kurilenko.dao.impl;

import com.kurilenko.dao.RoomDAO;
import com.kurilenko.entity.Room;
import com.kurilenko.entity.RoomInfo;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private MapperRS mapperRS;
    private final Connection connection;

    public RoomDAOImpl(){
        this.connection = DBConnection.getInstance().getConnction();
        this.mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Room room) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("")) {
            ResultSet resultSet = preparedStatement.executeQuery();



            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Room room) {

    }

    @Override
    public Room getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public List<RoomInfo> getAllInfoRooms(Long idHostel) {
        List<RoomInfo> listRooms = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from get_rooms_info(?)")) {
            preparedStatement.setInt(1, idHostel.intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listRooms.add(mapperRS.rowMapperRoomInfo(resultSet));
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return listRooms;
    }
}
