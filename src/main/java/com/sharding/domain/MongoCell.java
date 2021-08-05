package com.sharding.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author zhs
 * @Description
 * @createTime 2021/7/6 0006 16:59
 */

@Data
@Document(collection = "lbs_cell")
public class MongoCell implements Serializable {


    @Id
    private Long id;

    private Long ci;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint point;

    @Indexed
    private Integer mcc;

    @Indexed
    private Integer mnc;

    private Integer lac;

}
