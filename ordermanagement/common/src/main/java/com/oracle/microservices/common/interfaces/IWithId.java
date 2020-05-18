package com.oracle.microservices.common.interfaces;

import java.io.Serializable;

public interface IWithId<T extends Serializable> {

    T getId();
}
