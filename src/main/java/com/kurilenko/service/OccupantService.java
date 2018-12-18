package com.kurilenko.service;

import com.kurilenko.dao.OccupantDAO;
import com.kurilenko.dao.impl.OccupantDAOImpl;
import com.kurilenko.entity.Occupant;
import com.kurilenko.entity.OccupantProperty;
import com.kurilenko.utils.MapperRS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class OccupantService {
    private OccupantDAO occupantDAO;
    private MapperRS mapperRS;

    public OccupantService() {
        occupantDAO = new OccupantDAOImpl();
        mapperRS = MapperRS.getInstance();
    }


    public ObservableList<OccupantProperty> getAllOccupantProperty(Long id) {
        ObservableList<OccupantProperty> occupantProperties = FXCollections.observableArrayList();
        occupantProperties.addAll(occupantDAO.getAllInfoByIdHostel(id).stream().map(
                occupantInfo -> mapperRS.rowMapperOccupantProperty(occupantInfo)).collect(Collectors.toList()));
        return occupantProperties;
    }

    public ObservableList<OccupantProperty> getAllOccupantPropertyAdmin() {
        ObservableList<OccupantProperty> occupantProperties = FXCollections.observableArrayList();
        occupantProperties.addAll(occupantDAO.getAllInfoByIdHostelAdmin().stream().map(
                occupantInfo -> mapperRS.rowMapperOccupantProperty(occupantInfo)).collect(Collectors.toList()));
        return occupantProperties;
    }

    public Long save(Occupant occupant){
        return occupantDAO.save(occupant);
    }

    public void delete(Long id) {
        occupantDAO.delete(id);
    }
}
