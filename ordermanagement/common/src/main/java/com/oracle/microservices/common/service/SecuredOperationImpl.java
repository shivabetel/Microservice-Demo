package com.oracle.microservices.common.service;

import com.oracle.microservices.common.interfaces.ISecuredOperation;
import com.oracle.microservices.common.web.utils.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class SecuredOperationImpl implements ISecuredOperation {


    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public boolean verifyRequestWithToken(String criteriaParam, String criteriaValue) {
        if(criteriaParam != null){
            if(criteriaParam.equals("customerId")){
                Map userClaims = TokenHelper.getClaimsByKey("user", getToken());
                return String.valueOf(userClaims.get(criteriaParam)).equals(criteriaValue);
            }
        }

        return false;
    }


    private String getToken(){
       String authorizationHeader =  this.httpServletRequest.getHeader("Authorization");
       return authorizationHeader.replace("Bearer ", "");
    }
}
