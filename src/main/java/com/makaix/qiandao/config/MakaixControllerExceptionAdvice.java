package com.makaix.qiandao.config;

import com.makaix.qiandao.bean.vo.base.BaseResVo;
import com.makaix.qiandao.utils.e.MakaixException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class MakaixControllerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResVo exception(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResVo.faild(503, "内部错误");
    }

    @ResponseBody
    @ExceptionHandler(value = MakaixException.class)
    public BaseResVo exception1(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResVo.faild(450, e.getMessage());
    }
}
