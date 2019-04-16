package com.epam.hrynyshyn.services.impl;

import com.epam.hrynyshyn.repository.repositories.ManufacturerRepository;
import com.epam.hrynyshyn.model.entity.Manufacturer;
import com.epam.hrynyshyn.services.ManufacturerService;

import java.util.List;

public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository repository;

    public ManufacturerServiceImpl(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return repository.getAll();
    }
}