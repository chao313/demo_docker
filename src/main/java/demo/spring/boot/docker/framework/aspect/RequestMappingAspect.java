package demo.spring.boot.docker.framework.aspect;

import com.alibaba.fastjson.JSON;
import demo.spring.boot.docker.controller.aspect.AspectController;
import demo.spring.boot.docker.framework.annotations.CustomAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 2018/8/9    Created by   chao
 */
@org.aspectj.lang.annotation.Aspect
@Component
public class RequestMappingAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 使用注解定义切面执行的方法
     */
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointCut() {
    }


    @Around(value = "pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        logger.info("#执行结果:{}#", JSON.toJSON(result));
        return result;
        //这里的return的必须和拦截的方法的返回值一样
    }


}
