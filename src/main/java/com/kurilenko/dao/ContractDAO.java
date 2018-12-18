package com.kurilenko.dao;

import com.kurilenko.entity.Contract;
import com.kurilenko.entity.Settlement;

public interface ContractDAO extends BaseDAO<Contract, Long> {
    Long saveWithSettlement(Contract contract, Settlement settlement);
}
