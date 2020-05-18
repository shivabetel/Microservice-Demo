package com.oracle.microservices.common.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IOperations<T extends Serializable, E extends Serializable> {

    Optional<T> findById(E id);

    List<T> findAll();

    T save(T entity);

    List<T> saveAll(Iterable<T> entities);

}
