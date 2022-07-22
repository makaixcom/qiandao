package com.makaix.qiandao.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makaix.qiandao.bean.pojo.resource.ResourcePojo;
import com.makaix.qiandao.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Configuration
public class SpringSecurityConfig {

    private ObjectMapper objectMapper;

    private MakaixUserDetailsService makaixUserDetailsService;

    private AuthService authService;


    /**
     * 配置 spring security 的责任链
     *
     * @param http xx
     * @return xx
     * @throws Exception xx
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /* 登陆成功和失败的提示 */
        http.formLogin()
                .successHandler((req, res, au) -> write(res, 200, "恭喜你进入洞天福地"))
                .failureHandler((req, res, ex) -> write(res, 408, ex.getMessage()));

        /* 退出的提示 */
        http.logout()
                .logoutSuccessHandler((req, res, au) -> write(res, 200, "退出成功"));

        /* 受保护的资源和没有该权限的提示 */
        http.exceptionHandling()
                .authenticationEntryPoint((req, res, ex) -> write(res, 403, "登陆再访问啊，小老弟"))
                .accessDeniedHandler((req, res, ex) -> write(res, 405, "就算登陆了也没有权限，想一想，不充钱怎么能变强" + ex.getMessage()));

        /* 资源配置 和 角色列表 */
//        http.addFilterAt(filterSecurityInterceptor, FilterSecurityInterceptor.class);
        http.authorizeRequests()
                // 放行所有OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 放行登录方法
                .antMatchers("/**").permitAll()
                // 其他请求都需要认证后才能访问
                .anyRequest().authenticated();

        /* 用户 所拥有的角色 */
        http.userDetailsService(makaixUserDetailsService);

        /* 关闭那个丑B的登陆页，啥年代了，早就是前后端分离的项目了。 */
        http.csrf().disable();

        return http.build();
    }

//    @Bean
    FilterSecurityInterceptor filterSecurityInterceptor(AccessInterception accessInterception, AccessDecision accessDecision) {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager(accessDecision);
        filterSecurityInterceptor.setSecurityMetadataSource(accessInterception);
        return filterSecurityInterceptor;
    }

    @Bean
    AccessInterception accessInterception() {

        List<ResourcePojo> resources = authService.listAll();

        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = resources
                .stream()
                .collect(
                        Collectors.groupingBy(
                                e -> new AntPathRequestMatcher(e.getUrl(), e.getMethod()),
                                Collectors.mapping(e -> new SecurityConfig(e.getCode().trim()),
                                        Collectors.toCollection(LinkedHashSet::new)
                                )
                        )
                );

        return new AccessInterception(requestMap);
    }

    @Bean
    AccessDecision accessDecision() {
        return new AccessDecision();
    }


    /* 配置密码加密器 */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /* 图省事写个方法 */
    private void write(HttpServletResponse res, Integer code, String message) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        String s = objectMapper.writeValueAsString(map);
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.getWriter().write(s);
    }


}
