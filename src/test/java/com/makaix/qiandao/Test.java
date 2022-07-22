package com.makaix.qiandao;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class Test {
    public static void main(String[] args) {
//        String encode = new BCryptPasswordEncoder().encode("147");
//        System.out.println(encode);


        PathMatcher pathMatcher = new AntPathMatcher();
        boolean match = pathMatcher.match("/user/{id}/{value}", "/user/123/1");
        System.out.println(match);

    }
}
