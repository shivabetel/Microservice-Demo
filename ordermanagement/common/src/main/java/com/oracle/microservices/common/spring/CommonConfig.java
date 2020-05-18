package com.oracle.microservices.common.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.oracle.microservices.common.entities")
public class CommonConfig {
}
