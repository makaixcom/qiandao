package com.makaix.qiandao.utils.e;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MakaixExceptionUtils {

    public static MakaixException e(String msg){
        return new MakaixException(msg);
    }

    public static MakaixException args(String msg) {
        return new MakaixException(msg);
    }
}
