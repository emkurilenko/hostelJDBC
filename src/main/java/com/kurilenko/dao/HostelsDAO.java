package com.kurilenko.dao;

import com.kurilenko.entity.Hostels;

public interface HostelsDAO extends BaseDAO<Hostels, Long> {
    Hostels getHostelByIdUser(Long id);
    Hostels getOneByName(String name);
}
