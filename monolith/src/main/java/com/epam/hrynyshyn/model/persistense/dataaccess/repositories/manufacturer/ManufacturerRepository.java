package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.manufacturer;

import com.epam.hrynyshyn.model.persistense.entity.Manufacturer;

import java.util.List;

public interface ManufacturerRepository {
    List<Manufacturer> getAll();
}
