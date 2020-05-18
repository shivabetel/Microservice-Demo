package com.oracle.microservices.common.service;

import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.common.service.interfaces.IService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends IEntity, E extends Serializable> implements IService<T, E> {
    @Override
    public Optional<T> findById(E id) {
        return getDao().findById(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
       return getDao().saveAll(entities);
    }

    public abstract IJpaDao<T, E> getDao();
}
