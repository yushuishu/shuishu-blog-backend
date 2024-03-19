package com.shuishu.blog.common.utils.lock;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ：谁书-ss
 * @Date ：2024-03-15 8:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：service 默认是单例的，并发下lock只有一个实例
 * <p></p>
 */
@Slf4j
@Component
@Scope
@Aspect
@Order(1) //order越小越是最先执行，但更重要的是最先执行的最后结束
public class LockAspect {
    /**
     * 互斥锁 参数默认false，不公平锁
     */
    private static final Lock LOCK = new ReentrantLock(true);
    /**
     * Service层切点   用于记录错误日志
     */
    @Pointcut("@annotation(com.shuishu.blog.common.utils.lock.ServiceLock)")
    public void lockAspect() {

    }

    @Around("lockAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        boolean lockAcquired = false;
        try {
            lockAcquired = LOCK.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object obj = null;
        if (lockAcquired) {
            try {
                obj = joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } finally{
                LOCK.unlock();
            }
        } else {
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
        return obj;
    }

}
