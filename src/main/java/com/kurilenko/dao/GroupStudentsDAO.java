package com.kurilenko.dao;

import com.kurilenko.entity.GroupStudents;

public interface GroupStudentsDAO extends BaseDAO<GroupStudents, Long> {
    GroupStudents getOneByName(String name);
}
