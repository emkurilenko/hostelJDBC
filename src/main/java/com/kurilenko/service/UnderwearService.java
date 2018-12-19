package com.kurilenko.service;

import com.kurilenko.dao.UnderwearDAO;
import com.kurilenko.dao.impl.UnderwearDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.Collectors;

public class UnderwearService {
    private UnderwearDAO underwearDAO;

    public UnderwearService() {
        underwearDAO = new UnderwearDAOImpl();
    }

    public ObservableList<String> getAllNameUnderwearObservableList() {
        ObservableList<String> names = FXCollections.observableArrayList();
        names.addAll(underwearDAO.getAll().stream().map(underwear -> underwear.getNameUnderwerar()).collect(Collectors.toList()));
        return names;
    }

    public Long saveChoiceUnderwearByContract(String name, Long id) {
        return underwearDAO.saveUnderwearWithIdContract(name, id);
    }
}
