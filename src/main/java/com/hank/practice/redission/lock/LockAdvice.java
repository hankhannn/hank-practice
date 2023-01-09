/*
 * Copyright 2021-2021 www.jiahui.com
 */
package com.hank.practice.redission.lock;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author hank.han
 * @date 2023/01/09 - 14:23
 */

@Slf4j
@Aspect
@Component
public class LockAdvice {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(lock)")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Lock lock) throws Throwable {
        if (lock.leaseTime() < 0L) {
            throw new RuntimeException("expired time should not less than 0");
        }
        if (lock.leaseTime() > 0L) {
            return processWithLockTime(proceedingJoinPoint, lock);
        }
        return defaultProcess(proceedingJoinPoint, lock);
    }

    /**
     * generate lock key
     *
     * @param lock
     * @param parameterNames
     * @param args
     * @return
     */
    private String getKeyBySpel(Lock lock, String[] parameterNames, Object[] args) {
        String key = lock.key();
        if (key.contains("#")) {
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
            Expression expression = parser.parseExpression(key);
            Object expressionValue = expression.getValue(context);
            if (expressionValue == null) {
                throw new RuntimeException("generating key fail");
            }
            key = expressionValue.toString();
        }

        if (StringUtils.isNotBlank(lock.keyPrefix())) {
            key = lock.keyPrefix() + key;
        }
        return key;
    }

    private Object defaultProcess(ProceedingJoinPoint proceedingJoinPoint, Lock lock) throws Throwable {
        RLock rLock = null;
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(
                ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod());
        Object[] args = proceedingJoinPoint.getArgs();
        try {
            rLock = redissonClient.getLock(getKeyBySpel(lock, parameterNames, args));
            if (!rLock.tryLock()) {
                if (lock.ignoreLockFail()) {
                    return null;
                } else {
                    throw new RuntimeException("lock is existing");
                }
            }
            return proceedingJoinPoint.proceed();
        } finally {
            if (rLock != null) {
                try {
                    rLock.unlock();
                } catch (Exception e) {
                }
            }
        }
    }

    private Object processWithLockTime(ProceedingJoinPoint proceedingJoinPoint, Lock lock) throws Throwable {
        RLock rLock = null;
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(
                ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod());
        Object[] args = proceedingJoinPoint.getArgs();
        try {
            rLock = redissonClient.getLock(getKeyBySpel(lock, parameterNames, args));
            if (!rLock.tryLock(0, lock.leaseTime(), TimeUnit.SECONDS)) {
                if (lock.ignoreLockFail()) {
                    return null;
                } else {
                    throw new RuntimeException("lock fail");
                }
            }
            return proceedingJoinPoint.proceed();
        } finally {
            if (rLock != null && rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }

}
