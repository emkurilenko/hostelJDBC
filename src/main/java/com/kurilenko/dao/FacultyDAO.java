package com.kurilenko.dao;

import com.kurilenko.entity.Faculty;

public interface FacultyDAO extends BaseDAO<Faculty, Long> {
    Faculty getFacultyByName(String name);
}
