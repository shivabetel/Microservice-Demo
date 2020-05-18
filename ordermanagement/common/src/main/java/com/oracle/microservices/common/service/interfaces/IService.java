package com.oracle.microservices.common.service.interfaces;

import com.oracle.microservices.common.interfaces.IOperations;
import com.oracle.microservices.common.interfaces.IEntity;

import java.io.Serializable;

public interface IService<T extends IEntity, E extends Serializable> extends IOperations<T, E> {
}
