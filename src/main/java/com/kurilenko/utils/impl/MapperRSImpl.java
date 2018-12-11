package com.kurilenko.utils.impl;

import com.kurilenko.entity.*;
import com.kurilenko.entity.enums.Gender;
import com.kurilenko.entity.enums.OccupantType;
import com.kurilenko.entity.enums.StatusFamily;
import com.kurilenko.utils.MapperRS;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperRSImpl implements MapperRS {
    @Override
    public Contract rowMapperContract(ResultSet rs) throws SQLException {
        return new Contract(rs.getLong(1),
                rs.getString(2),
                rs.getDate(3),
                rs.getLong(4));
    }

    @Override
    public Department rowMapperDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getLong(1),
                rs.getString(2),
                rs.getLong(3));
    }

    @Override
    public Employees rowMapperEmployees(ResultSet rs) throws SQLException {
        return new Employees(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
    }

    @Override
    public ExistingInventory rowMapperExistingInventory(ResultSet rs) throws SQLException {
        return new ExistingInventory(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getDate(4));
    }

    @Override
    public Faculty rowMapperFaculty(ResultSet rs) throws SQLException {
        return new Faculty(rs.getLong(1), rs.getString(2));
    }

    @Override
    public Families rowMapperFamilies(ResultSet rs) throws SQLException {
        return new Families(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                StatusFamily.valueOf(rs.getString(3)));
    }

    @Override
    public Floors rowMapperFloors(ResultSet rs) throws SQLException {
        return new Floors(rs.getLong(1), rs.getLong(2));
    }

    @Override
    public GroupStudents rowMapperGroupStudents(ResultSet rs) throws SQLException {
        return new GroupStudents(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    @Override
    public Hostels rowMapperHostels(ResultSet rs) throws SQLException {
        return new Hostels(rs.getLong(1), rs.getString(2), rs.getString(3));
    }

    @Override
    public InventoryType rowMapperInventoryType(ResultSet rs) throws SQLException {
        return new InventoryType(rs.getLong(1), rs.getString(2));
    }

    @Override
    public IssuedLinen rowMapperIssuedLinen(ResultSet rs) throws SQLException {
        return new IssuedLinen(rs.getLong(1), rs.getLong(2));
    }

    @Override
    public Occupant rowMapperOccupant(ResultSet rs) throws SQLException {
        return new Occupant(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getDate(5),
                rs.getString(6),
                Gender.valueOf(rs.getString(7)),
                OccupantType.valueOf(rs.getString(8)));
    }

    @Override
    public Parents rowMapperParents(ResultSet rs) throws SQLException {
        return new Parents(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
    }

    @Override
    public Payment rowMapperPayment(ResultSet rs) throws SQLException {
        return new Payment(rs.getLong(1),
                rs.getLong(2),
                rs.getDate(3),
                rs.getDouble(4),
                rs.getBoolean(5));
    }

    @Override
    public Room rowMapperRoom(ResultSet rs) throws SQLException {
        return new Room(rs.getLong(1),
                rs.getLong(2),
                rs.getDouble(3),
                rs.getLong(4),
                rs.getLong(5),
                rs.getLong(6),
                rs.getLong(7));
    }

    @Override
    public RoomSpecification rowMapperRoomSpecification(ResultSet rs) throws SQLException {
        return new RoomSpecification(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    @Override
    public Settlement rowMapperSettlement(ResultSet rs) throws SQLException {
        return new Settlement(rs.getLong(1),
                rs.getDate(2),
                rs.getDate(3),
                rs.getLong(4),
                rs.getLong(5));
    }

    @Override
    public Specialty rowMapperSpecialty(ResultSet rs) throws SQLException {
        return new Specialty(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    @Override
    public Student rowMapperStudent(ResultSet rs) throws SQLException {
        return new Student(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    @Override
    public Underwear rowMapperUnderwear(ResultSet rs) throws SQLException {
        return new Underwear(rs.getLong(1), rs.getString(2));
    }
}
