package com.sharding.common.proxy.jdk;

/**
 * @author zhs
 * @Description 具体实现类
 * @createTime 2020/12/11 0011 11:04
 */
public class SubjectImpl implements Subject {

    @Override
    public void say(String name) {
        System.out.println("we are subject:" + name);
    }
}
