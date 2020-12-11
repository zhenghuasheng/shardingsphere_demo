package com.sharding.common.proxy.jdk;


import com.sharding.common.proxy.cglib.CgSubject;
import com.sharding.common.proxy.cglib.ProxyInterceptor;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhs
 * @Description 测试
 * @createTime 2020/12/11 0011 11:17
 */
public class ProxyTest {


    public static void main(String[] args) {
        Subject sub = new SubjectImpl();
        InvocationHandler subProxy = new SubjectProxy<>(sub);

        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subProxy.getClass().getClassLoader(), sub.getClass().getInterfaces(),
                subProxy);

        proxyInstance.say("zhs");


        Ldh ldh = new Ldh();
        subProxy = new SubjectProxy<>(ldh);
        Person personProxyInstance = (Person) Proxy.newProxyInstance(subProxy.getClass().getClassLoader(), ldh.getClass().getInterfaces(),
                subProxy);
        personProxyInstance.eat("fish");

        //cglib proxy
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CgSubject.class);
        enhancer.setCallback(new ProxyInterceptor());
        CgSubject cGsubject = (CgSubject) enhancer.create();
        cGsubject.say("zhs");
    }
}
