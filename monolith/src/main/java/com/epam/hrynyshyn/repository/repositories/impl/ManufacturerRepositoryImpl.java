package com.epam.hrynyshyn.repository.repositories.impl;

import com.epam.hrynyshyn.repository.TransactionManager;
import com.epam.hrynyshyn.model.entity.Manufacturer;
import com.epam.hrynyshyn.repository.repositories.ManufacturerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Queries.GET_ALL_MANUFACTURERS;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {
    private TransactionManager manager;

    public ManufacturerRepositoryImpl(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Manufacturer> getAll() {
        return manager.executeTransaction(connection -> {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_MANUFACTURERS);
            ResultSet resultSet = statement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer(resultSet.getInt(1), resultSet.getString(2));
                manufacturers.add(manufacturer);
            }
            return manufacturers;
        });
    }
}
