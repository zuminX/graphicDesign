package com.aspect;

import com.service.GraphicViewService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 异常信息的拦截器
 * 对异常信息进行拦截
 */
@Component
@Aspect
@Slf4j
public class ErrorInformationInterceptorProcess {

    private final GraphicViewService graphicViewService;

    /**
     * 注入成员变量
     */
    @Autowired
    public ErrorInformationInterceptorProcess(GraphicViewService graphicViewService) {
        this.graphicViewService = graphicViewService;
    }

    /**
     * 对异常进行拦截处理
     *
     * @param proceedingJoinPoint 连接点对象
     * @return 有异常->null 无异常->执行方法的返回值
     */
    @Around("execution(public * com.service.impl.*ServiceImpl.*(..))")
    public Object ErrorInformationProcess(ProceedingJoinPoint proceedingJoinPoint) {
        Object returnValue = null;
        try {
            //获取执行方法的参数
            final Object[] args = proceedingJoinPoint.getArgs();
            //执行被增强的方法
            returnValue = proceedingJoinPoint.proceed(args);
        } catch (Throwable throwable) {
            //捕捉异常，记录异常，显示异常信息给用户
            log.info(throwable.toString());
            graphicViewService.showErrorInformation(throwable.getMessage());
        }
        return returnValue;
    }

}
