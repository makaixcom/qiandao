package com.makaix.qiandao.utils.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DigestUtils {

    public static String sha1(String text){
        return text + "aaa";
    }
}
