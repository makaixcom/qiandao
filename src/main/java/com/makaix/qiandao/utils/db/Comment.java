package com.makaix.qiandao.utils.db;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Comment {

    String value() default "";
}
