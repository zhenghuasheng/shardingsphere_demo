package com.sharding.domain;

import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.context.annotation.Profile;
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
@Document(indexName = "lbs_cell",type = "cell")
public class CellInfo {

    @Id
    private Long cellId;

    @Field(type = FieldType.Text)
    private String address;

    //经度
    @Field(type = FieldType.Double)
    private Double longitude;
    //纬度
    @Field(type = FieldType.Double)
    private Double latitude;

    @GeoPointField
    private GeoPoint location;
}
