package com.makaix.qiandao.utils.e;

public class MakaixException extends RuntimeException {
    public MakaixException(String msg) {
        super(msg);
    }

    public MakaixException(String msg, Throwable t) {
        super(msg, t);
    }
}
