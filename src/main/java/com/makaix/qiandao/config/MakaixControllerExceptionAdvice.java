package com.makaix.qiandao.config;

import com.makaix.qiandao.bean.vo.base.BaseResVo;
import com.makaix.qiandao.utils.e.MakaixException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class MakaixControllerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler( Exception.class)
    public BaseResVo exception(Exception e) {
        log.error(e.getMessage(), e);
        return new BaseResVo(503, "内部错误");
    }

    @ResponseBody
    @ExceptionHandler( MakaixException.class)
    public BaseResVo exception1(Exception e) {
        log.error(e.getMessage(), e);
        return new BaseResVo(450, e.getMessage());
    }
}
