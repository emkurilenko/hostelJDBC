package com.kurilenko.dao.impl;

import com.kurilenko.dao.ContractDAO;
import com.kurilenko.entity.Contract;
import com.kurilenko.entity.Settlement;
import com.kurilenko.utils.DBConnection;
import com.kurilenko.utils.MapperRS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContractDAOImpl implements ContractDAO {
    private final String CREATE_CONTRACT_WITH_SETTLEMENT = "select * from create_contract_with_seettlement(?,?,?,?,?,?)";

    private Connection connection;
    private MapperRS mapperRS;

    public ContractDAOImpl() {
        connection = DBConnection.getInstance().getConnction();
        mapperRS = MapperRS.getInstance();
    }

    @Override
    public Long save(Contract contract) {
        return null;
    }

    public Long saveWithSettlement(Contract contract, Settlement settlement) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CONTRACT_WITH_SETTLEMENT)) {
            preparedStatement.setLong(1, contract.getFkOccupant());
            preparedStatement.setString(2, contract.getNumber());
            preparedStatement.setDate(3, java.sql.Date.valueOf(contract.getDateOfConclusion()));

            preparedStatement.setDate(4, java.sql.Date.valueOf(settlement.getDateOfSettlement()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(settlement.getDateOfEviction_()));
            preparedStatement.setLong(6, settlement.getFkRoom());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println("saveWithSettlement: " + e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Contract getOneById(Long aLong) {
        return null;
    }

    @Override
    public List<Contract> getAll() {
        return null;
    }
}
