package com.kurilenko.service;

import com.kurilenko.dao.ParentsDAO;
import com.kurilenko.dao.impl.ParentsDAOImpl;
import com.kurilenko.entity.Parents;

public class ParentService {
    private ParentsDAO parentsDAO;

    public ParentService() {
        parentsDAO = new ParentsDAOImpl();
    }

    public Long saveWithIdStudent(Long id, Parents parents) {
        return parentsDAO.saveWithIdStudent(id, parents);
    }

    public void deleteParent(Long id){
        parentsDAO.delete(id);
    }
}
