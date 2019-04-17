package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Manufacturer;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ManufacturerRepository extends Repository<Manufacturer,Integer> {
    List<Manufacturer> getAll();
}
