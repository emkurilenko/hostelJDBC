package com.kurilenko.dao;

import com.kurilenko.entity.Occupant;
import com.kurilenko.entity.OccupantInfo;

import java.util.List;

public interface OccupantDAO extends BaseDAO<Occupant,Long> {
    List<OccupantInfo> getAllInfoByIdHostel(Long id);
    List<OccupantInfo> getAllInfoByIdHostelAdmin();
}
