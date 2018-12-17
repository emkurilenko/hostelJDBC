package com.kurilenko.service;

import com.kurilenko.dao.HostelsDAO;
import com.kurilenko.dao.impl.HostelsDAOImpl;
import com.kurilenko.entity.Hostels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostelService {
    private HostelsDAO hostelsDAO;

    public HostelService(){
        hostelsDAO = new HostelsDAOImpl();
    }

    public List<String> getAllNameHostel(){
        return hostelsDAO.getAll().stream().map(hostels -> hostels.getNameHostel()).collect(Collectors.toList());
    }

    public List<String> getFloorsList(String name){
        List<String> list = new ArrayList<>();
        Long floors = hostelsDAO.getOneByName(name).getFloors();
        for (int i = 1; i <= floors; i++){
            list.add(String.valueOf(i));
        }
        return list;
    }

    public Hostels getHostelByName(String name){
        return hostelsDAO.getOneByName(name);
    }

    public Hostels getHostelByUserId(Long id){
        return hostelsDAO.getHostelByIdUser(id);
    }

}
