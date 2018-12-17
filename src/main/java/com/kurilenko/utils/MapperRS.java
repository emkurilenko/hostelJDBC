package com.kurilenko.utils;

import com.kurilenko.entity.*;
import com.kurilenko.entity.enums.Gender;
import com.kurilenko.entity.enums.OccupantType;
import com.kurilenko.entity.enums.StatusFamily;
import com.kurilenko.entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperRS {
    private static MapperRS singleton;

    private MapperRS() {
    }

    public static MapperRS getInstance() {
        if (singleton == null) {
            synchronized (MapperRS.class) {
                if (singleton == null) {
                    singleton = new MapperRS();
                }
            }
        }
        return singleton;
    }

    public Contract rowMapperContract(ResultSet rs) throws SQLException {
        return new Contract(rs.getLong(1),
                rs.getString(2),
                rs.getDate(3),
                rs.getLong(4));
    }

    public Department rowMapperDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getLong(1),
                rs.getString(2),
                rs.getLong(3));
    }

    public Employees rowMapperEmployees(ResultSet rs) throws SQLException {
        return new Employees(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));
    }

    public ExistingInventory rowMapperExistingInventory(ResultSet rs) throws SQLException {
        return new ExistingInventory(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getDate(4));
    }

    public Faculty rowMapperFaculty(ResultSet rs) throws SQLException {
        return new Faculty(rs.getLong(1), rs.getString(2));
    }

    public Families rowMapperFamilies(ResultSet rs) throws SQLException {
        return new Families(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                StatusFamily.valueOf(rs.getString(3)));
    }

    public Floors rowMapperFloors(ResultSet rs) throws SQLException {
        return new Floors(rs.getLong(1), rs.getLong(2));
    }

    public GroupStudents rowMapperGroupStudents(ResultSet rs) throws SQLException {
        return new GroupStudents(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    public Hostels rowMapperHostels(ResultSet rs) throws SQLException {
        return new Hostels(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4));
    }

    public InventoryType rowMapperInventoryType(ResultSet rs) throws SQLException {
        return new InventoryType(rs.getLong(1), rs.getString(2));
    }

    public IssuedLinen rowMapperIssuedLinen(ResultSet rs) throws SQLException {
        return new IssuedLinen(rs.getLong(1), rs.getLong(2));
    }

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

    public Parents rowMapperParents(ResultSet rs) throws SQLException {
        return new Parents(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
    }

    public Payment rowMapperPayment(ResultSet rs) throws SQLException {
        return new Payment(rs.getLong(1),
                rs.getLong(2),
                rs.getDate(3),
                rs.getDouble(4),
                rs.getBoolean(5));
    }

    public Room rowMapperRoom(ResultSet rs) throws SQLException {
        return new Room(rs.getLong(1),
                rs.getLong(2),
                rs.getDouble(3),
                rs.getLong(4),
                rs.getLong(5),
                rs.getLong(6),
                rs.getLong(7));
    }

    public RoomSpecification rowMapperRoomSpecification(ResultSet rs) throws SQLException {
        return new RoomSpecification(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    public Settlement rowMapperSettlement(ResultSet rs) throws SQLException {
        return new Settlement(rs.getLong(1),
                rs.getDate(2),
                rs.getDate(3),
                rs.getLong(4),
                rs.getLong(5));
    }

    public Specialty rowMapperSpecialty(ResultSet rs) throws SQLException {
        return new Specialty(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    public Student rowMapperStudent(ResultSet rs) throws SQLException {
        return new Student(rs.getLong(1), rs.getString(2), rs.getLong(3));
    }

    public Underwear rowMapperUnderwear(ResultSet rs) throws SQLException {
        return new Underwear(rs.getLong(1), rs.getString(2));
    }

    public RoomInfo rowMapperRoomInfo(ResultSet rs) throws SQLException {
        return new RoomInfo(rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                rs.getLong(5),
                rs.getLong(6));
    }

    public UserInfo rowMapperUserInfo(ResultSet resultSet) throws SQLException {
        return new UserInfo(resultSet.getLong(1),
                resultSet.getString(2),
                UserRole.valueOf(resultSet.getString(3)),
                resultSet.getString(4));
    }

    public OccupantInfo rowMapperOccupantInfo(ResultSet resultSet) throws SQLException {
        return new OccupantInfo(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getLong(5),
                resultSet.getString(6));
    }

    public OccupantProperty rowMapperOccupantProperty(OccupantInfo occupantInfo) {
        String name = occupantInfo.getFirstName() + " " + occupantInfo.getMiddleName() + " " + occupantInfo.getLastName();
        return new OccupantProperty(occupantInfo.getId(), name, occupantInfo.getRoomNumber(), occupantInfo.getTypeOccupant());
    }
}
