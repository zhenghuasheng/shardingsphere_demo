package com.sharding.common.observer;

import java.util.Observable;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 14:15
 */
public class RealSubject extends Observable {



    public void doSomeThing(String name) {
        System.out.println(name+ " do someThing!!!");

        setChanged();
        notifyObservers(name);
    }

}
