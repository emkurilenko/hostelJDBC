package com.kurilenko.service;

import com.kurilenko.dao.StudentDAO;
import com.kurilenko.dao.impl.StudentDAOImpl;
import com.kurilenko.entity.Student;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService(){
        studentDAO = new StudentDAOImpl();
    }

    public Long save(Student student){
        return studentDAO.save(student);
    }

    public void delete(Long id){
        studentDAO.delete(id);
    }

    public Student getByID(Long idOccupant) {
        return studentDAO.getOneById(idOccupant);
    }
}
