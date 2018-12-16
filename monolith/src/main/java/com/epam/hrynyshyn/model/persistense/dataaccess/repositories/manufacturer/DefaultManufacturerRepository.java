package com.epam.hrynyshyn.model.persistense.dataaccess.repositories.manufacturer;

import com.epam.hrynyshyn.model.persistense.dataaccess.TransactionManager;
import com.epam.hrynyshyn.model.persistense.entity.Manufacturer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.Queries.GET_ALL_MANUFACTURERS;

public class DefaultManufacturerRepository implements ManufacturerRepository {
    private TransactionManager manager;

    public DefaultManufacturerRepository(TransactionManager manager) {
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
