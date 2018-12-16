package com.kurilenko.dao;

import java.util.List;

public interface BaseDAO<A, B> {
    Long save(A a);
    void delete(Long id);
    A getOneById(B b);
    List<A> getAll();
}
