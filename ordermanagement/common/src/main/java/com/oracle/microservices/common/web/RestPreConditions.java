package com.oracle.microservices.common.web;

import com.oracle.microservices.common.web.exception.OperationForbiddenException;

public final class RestPreConditions {

    public static void checkOperationAllowed(final boolean expression) {
       checkOperationAllowed(expression, null);
    }


    public static void checkOperationAllowed(final boolean expression, final String message) {
        if (!expression) {
            throw new OperationForbiddenException(message != null ? message : "Operation not allowed");
        }
    }
}
