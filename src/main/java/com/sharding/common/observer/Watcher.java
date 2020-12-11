package com.sharding.common.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhs
 * @Description
 * @createTime 2020/12/11 0011 14:40
 */
public class Watcher implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof RealSubject) {
            System.out.println("arg:"+ arg);
        }
    }
}
