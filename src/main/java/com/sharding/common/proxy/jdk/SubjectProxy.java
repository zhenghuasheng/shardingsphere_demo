package com.sharding.common.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 11:06
 */
public class SubjectProxy<T> implements InvocationHandler {

    private T subject;

    public SubjectProxy(T subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----------------start-----------"+ System.currentTimeMillis());
        Object obj = method.invoke(subject, args);
        System.out.println("-----------------end------------"+ System.currentTimeMillis());
        return obj;
    }
}
