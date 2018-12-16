package com.kurilenko.service;

import com.kurilenko.dao.OccupantDAO;
import com.kurilenko.dao.impl.OccupantDAOImpl;

public class OccupantService {
    private OccupantDAO occupantDAO;

    public OccupantService(){
        occupantDAO = new OccupantDAOImpl();
    }


}
