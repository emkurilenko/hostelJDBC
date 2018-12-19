package com.kurilenko.dao.impl;

import com.kurilenko.dao.StudentDAO;
import com.kurilenko.entity.Student;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private String INSERT_STUDENT = "select * from insert_into_student(?,?,?)";
    private String DELETE_BY_ID = "delete from student where id = ?";
    private String SELECT_BY_ID = "select * from student where id = ?";

    private Connection connection;
    private MapperRS mapperRS;

    public StudentDAOImpl() {
        this.connection = DBConnection.getInstance().getConnction();
        this.mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Student student) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getRecordNumber());
            preparedStatement.setLong(3, student.getFkGroup());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("SAVE Student: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("DELETE: " + e.getMessage());
        }

    }

    @Override
    public Student getOneById(Long aLong) {
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)){
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = mapperRS.rowMapperStudent(resultSet);
            resultSet.close();
        }catch (SQLException e){
            System.out.println("STUDENT" + e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
