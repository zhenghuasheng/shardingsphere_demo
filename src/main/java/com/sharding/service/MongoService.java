package com.sharding.service;

import com.mongodb.client.model.geojson.Position;
import com.sharding.domain.MongoWifi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author zhs
 * @Description
 * @createTime 2021/7/6 0006 13:37
 */
@Service
public class MongoService {

    @Autowired
    private MongoWifiRepository mongoWifiRepository;


    @PostConstruct
    public void init() throws InterruptedException {


//        GeoJsonPoint p = new GeoJsonPoint(112.23, 48.9);
//        MongoWifi mw = new MongoWifi();
//        mw.setAddress("湖南省长沙市岳麓区中电软件园");
//        mw.setMac("111111111111111111");
//        mw.setPoint(p);
//        mongoWifiRepository.save(mw);




            long time = System.currentTimeMillis();
            Point point = new Point(121.028058, 33.3331);
            Distance distance = new Distance(2, Metrics.KILOMETERS);
            GeoResults<MongoWifi> list = mongoWifiRepository.findByPointNear(point, distance);
            System.out.println(list);
            System.out.println(System.currentTimeMillis() - time);




    }
}
