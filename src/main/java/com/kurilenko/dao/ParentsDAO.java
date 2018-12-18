package com.kurilenko.dao;

import com.kurilenko.entity.Parents;

public interface ParentsDAO extends BaseDAO<Parents, Long> {
    public Long saveWithIdStudent(Long id, Parents parents);
}
