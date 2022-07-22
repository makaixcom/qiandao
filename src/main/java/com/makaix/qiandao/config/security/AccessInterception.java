package com.makaix.qiandao.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.*;

/**
 * 主要作用是将当前请求需要的权限返回
 */

public class AccessInterception implements FilterInvocationSecurityMetadataSource {



    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    public AccessInterception(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap){
        this.requestMap = requestMap;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        Iterator<Map.Entry<RequestMatcher, Collection<ConfigAttribute>>> iterator = this.requestMap.entrySet().iterator();

        String method = request.getMethod();

        if(method.equals("OPTIONS")){
            return null;
        }

        while (iterator.hasNext()) {
            Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry = iterator.next();
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        throw new AccessDeniedException("没有该权限");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        Collection<Collection<ConfigAttribute>> values = this.requestMap.values();
        Objects.requireNonNull(allAttributes);
        values.forEach(allAttributes::addAll);
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
