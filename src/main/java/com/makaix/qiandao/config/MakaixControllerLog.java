package com.makaix.qiandao.config;

import com.makaix.qiandao.bean.vo.base.BaseResVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class MakaixControllerLog {

    @Around("execution(public * com.makaix.qiandao.controller..*Controller.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = getRequestByPjp();
        String url = request.getRequestURI();
        String queryString = request.getQueryString();
        Object result;
        long start = System.currentTimeMillis();

        try {
            result = pjp.proceed();
            BaseResVo b = (BaseResVo) result;
            b.setResCode(200);
        } finally {
            log.info("url: {}, time: {}, queryString: {}", url, System.currentTimeMillis() - start,  queryString);
        }
        return result;
    }

    /**
     * 通过PJP，获取Request
     *
     * @return 返回request
     */
    private HttpServletRequest getRequestByPjp() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if(ra == null){
            throw new NullPointerException("http servlet request is null");
        }
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }
}
