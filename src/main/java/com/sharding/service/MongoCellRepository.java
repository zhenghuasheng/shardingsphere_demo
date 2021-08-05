package com.sharding.service;

import com.sharding.domain.MongoCell;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhs
 * @Description
 * @createTime 2021/7/6 0006 17:05
 */
public interface MongoCellRepository extends MongoRepository<MongoCell,Long> {

    GeoResults<MongoCell> findByPointNear(Point location, Distance distance);
}
