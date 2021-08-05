package com.sharding.service;


import com.sharding.domain.MongoWifi;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhs
 * @Description
 * @createTime 2021/7/6 0006 11:35
 */
public interface MongoWifiRepository extends MongoRepository<MongoWifi, String> {

    GeoResults<MongoWifi> findByPointNear(Point location, Distance distance);

}
