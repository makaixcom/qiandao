package com.makaix.qiandao.bean.vo.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResVo {

    private Integer resCode;

    private String resMsg;


    public static BaseResVo ok(){
        return new BaseResVo(200, null);
    }


    public static BaseResVo faild(String message){
        return new BaseResVo(451, message);
    }

    public static BaseResVo faild(Integer code, String message){
        return new BaseResVo(code, message);
    }

}
