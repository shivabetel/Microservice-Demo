package com.oracle.microservices.common.interfaces;

public interface ISecuredOperation {

    boolean verifyRequestWithToken(String criteriaParam, String criteriaValue);
}
