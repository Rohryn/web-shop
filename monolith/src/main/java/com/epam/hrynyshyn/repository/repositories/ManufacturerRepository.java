package com.epam.hrynyshyn.repository.repositories;

import com.epam.hrynyshyn.model.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    List<Manufacturer> findByNames(List<String> names);
}
