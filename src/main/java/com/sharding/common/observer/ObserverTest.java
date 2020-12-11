package com.sharding.common.observer;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 14:42
 */
public class ObserverTest {


    public static void main(String[] args) {
        RealSubject subject = new RealSubject();

        Watcher watcher = new Watcher();
        subject.addObserver(watcher);

        subject.doSomeThing("0000000000000000000000000");
    }
}
