package com.sharding.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;

@Data
@Document(indexName = "lbs_wifi",type = "wifi",refreshInterval = "30s" ,shards = 9, replicas = 0)
public class WifiInfo implements Serializable {

    @Id
    private String mac;

    //经度
    @Field(type = FieldType.Double)
    private Double longitude;
    //纬度
    @Field(type = FieldType.Double)
    private Double latitude;


    @Field(type = FieldType.Text)
    private String address;


    @Field(type = FieldType.Integer)
    private Integer acc;


    @Field(type = FieldType.Text)
    private String updateTime;


    @GeoPointField
    private GeoPoint location;
}
