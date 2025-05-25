package com.example.Spring_core_tasks.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimedAspect {
    private final MetricStore store;

    public TimedAspect(MetricStore store) {
        this.store = store;
    }

    @Around("@within(com.example.Spring_core_tasks.metrics.Timed) || @annotation(com.example.Spring_core_tasks.metrics.Timed)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long took = System.currentTimeMillis() - start;
        store.record(pjp.getSignature().toShortString(), took);
        return result;
    }
}




