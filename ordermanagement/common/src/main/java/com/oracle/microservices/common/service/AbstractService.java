package com.oracle.microservices.common.service;

import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.common.search.ClientOperation;
import com.oracle.microservices.common.service.interfaces.IService;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.ArrayList;
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
    public T create(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void update(T entity) {
        getDao().save(entity);
    }


    @Override
    public Optional<T> searchOne(Triple<String, ClientOperation, String>... constraints) {
        Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specification<T> specifications = Specification.where(firstSpec);
        for(int i = 1; i< constraints.length; i++){
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return Optional.empty();
        }
        return getSpecificationExecutor().findOne(specifications);
    }

    @Override
    public List<T> searchAll(Triple<String, ClientOperation, String>... constraints) {
        Specification<T> firstSpec = resolveConstraint(constraints[0]);
        Specification<T> specifications = Specification.where(firstSpec);
        for(int i = 1; i< constraints.length; i++){
            specifications = specifications.and(resolveConstraint(constraints[i]));
        }
        if (firstSpec == null) {
            return new ArrayList<>();
        }
        return getSpecificationExecutor().findAll(specifications);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
       return getDao().saveAll(entities);
    }

    public Specification<T> resolveConstraint(final Triple<String, ClientOperation, String> constraint) {
        throw new UnsupportedOperationException();
    }

    public abstract IJpaDao<T, E> getDao();

    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();
}
