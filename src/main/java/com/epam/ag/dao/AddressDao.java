package com.epam.ag.dao;

import com.epam.ag.model.user.Address;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface AddressDao extends GenericDao<Address> {
    @Override
    Address save(Address entity);

    @Override
    Address getByParameter(String param, String value);

    @Override
    Address getById(Long id);

    @Override
    List<Address> getAll();

    @Override
    boolean delete(Address entity);
}
