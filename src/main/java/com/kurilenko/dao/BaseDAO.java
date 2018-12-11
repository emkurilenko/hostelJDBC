package com.kurilenko.dao;

import java.util.List;

public interface BaseDAO<A, B> {
    Long save(A a);
    void delete(A a);
    A getOneById(B b);
    List<A> getAll();
}
