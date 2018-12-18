package com.kurilenko.service;

import com.kurilenko.dao.ContractDAO;
import com.kurilenko.dao.impl.ContractDAOImpl;
import com.kurilenko.entity.Contract;
import com.kurilenko.entity.Settlement;

public class ContractService {
    private ContractDAO contractDAO;

    public ContractService(){
        contractDAO = new ContractDAOImpl();
    }

    public Long createContractWithSettlement(Contract contract, Settlement settlement){
        return contractDAO.saveWithSettlement(contract, settlement);
    }
}
