package com.oracle.microservices.commontest.service;


import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.service.interfaces.IService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("classpath:persistence-test.properties")
public abstract class AbstractServiceIntegrationTest<T extends IEntity, E extends Serializable> {



   @Test
   @Disabled
    protected void givenResourceExists_whenResourceIsRetrievedById_thenResourceIsFound(){
       T resource = createEntity();
       getApi().save(resource);
       assertNotNull(getApi().findById((E) resource.getId()).orElseGet(() -> null));
    }

    protected abstract IService<T, E> getApi();
   protected abstract T createEntity();
}
