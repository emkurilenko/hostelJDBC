package com.kurilenko.dao;

import com.kurilenko.entity.Department;

public interface DepartmentDAO extends BaseDAO<Department, Long> {
    Department getDepartmentByName(String name);
}
