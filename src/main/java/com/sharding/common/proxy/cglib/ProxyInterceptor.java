package com.sharding.common.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 13:48
 */
public class ProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib proxy start----------------"+ System.currentTimeMillis());
        System.out.println("proxy target method:"+ method.getName());
        Object invoker = methodProxy.invokeSuper(o, objects);

        System.out.println("cglib proxy end----------------"+ System.currentTimeMillis());
        return invoker;
    }
}
