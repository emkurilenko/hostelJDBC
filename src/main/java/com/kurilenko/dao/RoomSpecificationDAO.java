package com.kurilenko.dao;

import com.kurilenko.entity.RoomSpecification;

public interface RoomSpecificationDAO extends BaseDAO<RoomSpecification, Long> {
    RoomSpecification getOneByType(String name);
}
