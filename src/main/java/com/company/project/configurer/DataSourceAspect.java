package com.company.project.configurer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * aop 动态切换数据源
 * </p>
 *
 * @author wangdejian
 * @since 2018/3/19
 */
@Aspect
@Component
@Order(-1)// 保证该AOP在@Transactional之前执行
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 切点: 所有配置 DataSource 注解的方法
     */
    @Pointcut("@annotation(com.company.project.configurer.DynamicSource)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DynamicSource ds = method.getAnnotation(DynamicSource.class);
        // 通过判断 DataSource 中的值来判断当前方法应用哪个数据源
        DynamicDataSource.setDataSource(ds.value());
        logger.info("当前数据源: " + ds.value());
        logger.info("set datasource is " + ds.value());
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.info("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
