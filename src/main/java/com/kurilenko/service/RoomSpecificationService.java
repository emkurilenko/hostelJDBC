package com.kurilenko.service;

import com.kurilenko.dao.RoomSpecificationDAO;
import com.kurilenko.dao.impl.RoomSpecificationDAOImpl;
import com.kurilenko.entity.RoomSpecification;

import java.util.List;
import java.util.stream.Collectors;

public class RoomSpecificationService {
    private RoomSpecificationDAO roomSpecificationDAO;
    public RoomSpecificationService(){
        roomSpecificationDAO = new RoomSpecificationDAOImpl();
    }

    public List<String> getAllNameSpecification(){
        return roomSpecificationDAO.getAll().stream().map(roomSpecification -> roomSpecification.getTypeOfRoom()).collect(Collectors.toList());
    }

    public Long getNumberOfBedsInRoomSpecification(String type){
        return roomSpecificationDAO.getOneByType(type).getNumberOfBeds();
    }

    public RoomSpecification getBySpecification(String type){
        return roomSpecificationDAO.getOneByType(type);
    }
}
