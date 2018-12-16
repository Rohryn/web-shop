package com.epam.hrynyshyn.model.services.manufacturer;

import com.epam.hrynyshyn.model.persistense.dataaccess.repositories.manufacturer.ManufacturerRepository;
import com.epam.hrynyshyn.model.persistense.entity.Manufacturer;

import java.util.List;

public class DefaultManufacturerService implements ManufacturerService {
    private ManufacturerRepository repository;

    public DefaultManufacturerService(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return repository.getAll();
    }
}
