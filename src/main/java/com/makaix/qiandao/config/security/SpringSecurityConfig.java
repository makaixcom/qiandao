package com.makaix.qiandao.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private ObjectMapper objectMapper;


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
                .accessDeniedHandler((req, res, ex) -> write(res, 405, "就算登陆了也没有权限，想一想，不充钱怎么能变强"));

        /* 资源配置 和 角色列表，目前测试图省事，马上就换成数据库 */
        String adminStr = "admin";
        String userStr = "user";
        http.authorizeRequests()
                .antMatchers("/user/list").hasAuthority(userStr)
                .antMatchers("/role/list").hasAuthority(adminStr)
                .anyRequest().authenticated();

        /* 用户 所拥有的角色 */
        String rawPassword = "123";
        UserDetails user = User.builder().username(userStr).password(passwordEncoder().encode(rawPassword)).authorities(userStr).build();
        UserDetails admin = User.builder().username(adminStr).password(passwordEncoder().encode(rawPassword)).authorities(adminStr).build();
        http.userDetailsService(new InMemoryUserDetailsManager(user, admin));

        /* 关闭那个丑B的登陆页，啥年代了，早就是前后端分离的项目了。 */
        http.csrf().disable();

        return http.build();
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
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(s);
    }


}
