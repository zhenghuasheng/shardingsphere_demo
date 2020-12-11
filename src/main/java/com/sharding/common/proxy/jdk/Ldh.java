package com.sharding.common.proxy.jdk;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 11:35
 */
public class Ldh implements Person{
    @Override
    public void eat(String food) {
        System.out.println("ldh eat:" + food);
    }
}
