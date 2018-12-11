package com.kurilenko.utils;

import com.kurilenko.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperRS {
    Contract rowMapperContract(ResultSet rs) throws SQLException;
    Department rowMapperDepartment(ResultSet rs) throws SQLException;
    Employees rowMapperEmployees(ResultSet rs) throws SQLException;
    ExistingInventory rowMapperExistingInventory(ResultSet rs) throws SQLException;
    Faculty rowMapperFaculty(ResultSet rs) throws SQLException;
    Families rowMapperFamilies(ResultSet rs) throws SQLException;
    Floors rowMapperFloors(ResultSet rs) throws SQLException;
    GroupStudents rowMapperGroupStudents(ResultSet rs) throws SQLException;
    Hostels rowMapperHostels(ResultSet rs) throws SQLException;
    InventoryType rowMapperInventoryType(ResultSet rs) throws SQLException;
    IssuedLinen rowMapperIssuedLinen(ResultSet rs) throws SQLException;
    Occupant rowMapperOccupant(ResultSet rs) throws SQLException;
    Parents rowMapperParents(ResultSet rs) throws SQLException;
    Payment rowMapperPayment(ResultSet rs) throws SQLException;
    Room rowMapperRoom(ResultSet rs) throws SQLException;
    RoomSpecification rowMapperRoomSpecification(ResultSet rs) throws SQLException;
    Settlement rowMapperSettlement(ResultSet rs) throws SQLException;
    Specialty rowMapperSpecialty(ResultSet rs) throws SQLException;
    Student rowMapperStudent(ResultSet rs) throws SQLException;
    Underwear rowMapperUnderwear(ResultSet rs) throws SQLException;
}
