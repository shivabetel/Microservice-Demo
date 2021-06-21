package com.oracle.microservices.common.service.interfaces;

import com.oracle.microservices.common.interfaces.IOperations;
import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.search.ClientOperation;
import org.apache.commons.lang3.tuple.Triple;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IService<T extends IEntity, E extends Serializable> extends IOperations<T, E> {

    Optional<T> searchOne(Triple<String, ClientOperation, String>... constraints);
    List<T> searchAll(Triple<String, ClientOperation, String>... constraints);
}
