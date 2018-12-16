package com.epam.hrynyshyn.model.services.manufacturer;

import com.epam.hrynyshyn.model.persistense.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturers();
}
