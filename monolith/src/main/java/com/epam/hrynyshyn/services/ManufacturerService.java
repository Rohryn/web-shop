package com.epam.hrynyshyn.services;

import com.epam.hrynyshyn.model.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturers();

    List<Manufacturer> getByNames(List<String> names);
}
