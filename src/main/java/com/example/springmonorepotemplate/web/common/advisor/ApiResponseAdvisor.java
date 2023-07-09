package com.example.springmonorepotemplate.web.common.advisor;

import com.example.springmonorepotemplate.web.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

@Slf4j
@RestControllerAdvice
public class ApiResponseAdvisor implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Type type = GenericTypeResolver.resolveType(
                getGenericType(returnType),
                returnType.getContainingClass()
        );

        return !Void.class.getName().equals(type.getTypeName());
    }

    private Type getGenericType(MethodParameter returnType) {
        if (HttpEntity.class.isAssignableFrom(returnType.getParameterType())) {
            return ResolvableType.forType(returnType.getGenericParameterType()).getGeneric().getType();
        }
        return returnType.getGenericParameterType();
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        ApiResponse<Object> apiResponse = ApiResponse.from(body);
        log.debug("[ApiResponse] {}", apiResponse);

        return apiResponse;
    }
}
