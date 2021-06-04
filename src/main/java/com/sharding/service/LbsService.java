package com.sharding.service;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhs
 * @Description
 * @createTime 2021/6/4 0004 15:26
 */
@Slf4j
public class LbsService {


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2000),
            new ThreadPoolExecutor.CallerRunsPolicy());





    public void init() {
        try {
            FileReader fileReader = new FileReader("");
            BufferedReader in = new BufferedReader(fileReader);

            String line = null;
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
