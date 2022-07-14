package com.makaix.qiandao.utils.other;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MakaixBeanUtils {

    @SneakyThrows
    public static  <T> T copy(Object target, Class<T> clazz) {
        T t = clazz.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(target, t);
        return t;
    }
}
