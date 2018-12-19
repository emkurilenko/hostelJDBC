package com.kurilenko.dao;

import com.kurilenko.entity.Underwear;

public interface UnderwearDAO extends BaseDAO<Underwear, Long> {
    Long saveUnderwearWithIdContract(String nameUnderwear, Long idContract);
    Underwear getByNameUnderwear(String name);
}
