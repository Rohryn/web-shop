package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Manufacturer;

import java.util.List;

public interface ManufacturerRepository {
    List<Manufacturer> getAll();
}
