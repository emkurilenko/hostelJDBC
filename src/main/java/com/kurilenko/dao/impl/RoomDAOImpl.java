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

    private String INSERT_ROOM = "insert into room (id,number_room,square,fk_room_specification,number,fk_hostel,floor) values (DEFAULT,?,?,?,?,?,?) returning id";
    private MapperRS mapperRS;
    private final Connection connection;

    public RoomDAOImpl() {
        this.connection = DBConnection.getInstance().getConnction();
        this.mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Room room) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROOM)) {
            preparedStatement.setLong(1, room.getNumberRoom());
            preparedStatement.setDouble(2, room.getSquare());
            preparedStatement.setLong(3, room.getRoomSpecification());
            preparedStatement.setLong(4, room.getNumber());
            preparedStatement.setLong(5, room.getFkHostel());
            preparedStatement.setLong(6, room.getFloors());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long room) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from room where id = ?")) {
            preparedStatement.setLong(1, room);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from get_rooms_info(?)")) {
            preparedStatement.setInt(1, idHostel.intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRooms.add(mapperRS.rowMapperRoomInfo(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listRooms;
    }
}
