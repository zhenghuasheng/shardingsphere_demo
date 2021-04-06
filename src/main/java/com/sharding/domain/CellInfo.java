package com.sharding.domain;

import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

/**
 * @author zhs
 * @Description
 * @createTime 2021/3/8 0008 09:30
 */

@Data
@Document(indexName = "lbs_cell",type = "cell", shards = 6, replicas = 0, refreshInterval = "30s")
public class CellInfo {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long ci;

    @Field(type = FieldType.Text)
    private String address;

    //经度
    @Field(type = FieldType.Double)
    private Double lon;
    //纬度
    @Field(type = FieldType.Double)
    private Double lat;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Text)
    private String radio;

    @Field(type = FieldType.Integer)
    private Integer mcc;

    @Field(type = FieldType.Integer)
    private Integer mnc;

    @Field(type = FieldType.Integer)
    private Integer lac;

    @Field(type = FieldType.Integer)
    private Integer range;

    @Field(type = FieldType.Integer)
    private Integer samples;
}
