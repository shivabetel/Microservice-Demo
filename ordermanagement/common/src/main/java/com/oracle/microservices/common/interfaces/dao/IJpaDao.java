package com.oracle.microservices.common.interfaces.dao;

import com.oracle.microservices.common.interfaces.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface IJpaDao<T extends IEntity, E extends Serializable> extends JpaRepository<T, E> {
}
