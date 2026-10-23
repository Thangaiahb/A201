package com.rest.service;

import java.util.List;

import com.rest.models.Table;
import com.rest.repository.TableRepository;

public class TableServices {
    private final TableRepository repository;

    public TableServices() {
        repository = new TableRepository();
    }

    public List<Table> getAll() {
        return repository.show();
    }

}
