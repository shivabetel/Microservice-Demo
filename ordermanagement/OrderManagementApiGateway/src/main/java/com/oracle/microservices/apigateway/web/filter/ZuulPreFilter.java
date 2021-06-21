package com.oracle.microservices.apigateway.web.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.oracle.microservices.apigateway.security.interfaces.ITokenValidation;
import com.oracle.microservices.apigateway.web.dto.AuthenticationResultDTO;
import com.oracle.microservices.common.web.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


@Component
public class ZuulPreFilter extends ZuulFilter {

    private static List<String> nonProtectedresource = Arrays.asList("/api/login", "/api/registration", "/api/product");

    private ITokenValidation tokenValidation;

    public ZuulPreFilter(ITokenValidation tokenValidation) {
        this.tokenValidation = tokenValidation;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
      RequestContext requestContext =   RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        if(isProtectedResource(request.getRequestURI())){
           AuthenticationResultDTO authenticationResultDTO =  tokenValidation.validateToken(request.getHeader("Authorization"));
            if(authenticationResultDTO.getUser() == null){
                ErrorDTO dto = new ErrorDTO();
                dto.setResponseCode(HttpStatus.UNAUTHORIZED.toString());
                dto.setResponseMessage("Not a valid token");
                try {
                    requestContext.setResponseBody(new ObjectMapper().writeValueAsString(dto));
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    //requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                    requestContext.setResponse(response);
                    requestContext.setSendZuulResponse(false);
                } catch (JsonProcessingException e) {
                    throw  new ZuulException("Processing error", 400, "Error while Converting object to JSON");
                }
            }
        }
        return null;
    }

    private boolean isProtectedResource(String requestUri){
        for (String resource : ZuulPreFilter.nonProtectedresource) {
            if(requestUri.contains(resource)){
                return false;
            }
        }

        return true;
    }


}
