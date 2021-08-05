package com.sharding.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author zhs
 * @Description
 * @createTime 2021/7/6 0006 11:26
 */

@Data
@Document(collection = "lbs_wifi")
public class MongoWifi implements Serializable {

    @Id
    private String mac;


    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint point;


    private String address;

}
