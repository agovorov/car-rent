package com.epam.ag.dao;

import com.epam.ag.model.Order;
import com.epam.ag.utils.SqlParams;

import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public interface OrderDao extends GenericDao<Order> {
    @Override
    Order save(Order entity);

    @Override
    Order getByParameter(String param, String value);

    @Override
    Order getById(Long id);

    @Override
    List<Order> getAll();

    @Override
    boolean delete(Order entity);

    List<Order> getAllByParameters(Map<String, SqlParams> mapParams);
}
