package com.kurilenko.service;

import com.kurilenko.dao.EmployeesDAO;
import com.kurilenko.dao.impl.EmployeesDAOImpl;
import com.kurilenko.entity.Employees;

public class EmployeesService {
    private EmployeesDAO employeesDAO;

    public EmployeesService() {
        employeesDAO = new EmployeesDAOImpl();
    }

    public Long save(Employees employees){
        return employeesDAO.save(employees);
    }

    public void delete(Long id){
        employeesDAO.delete(id);
    }
}
