package com.sharding.domain;

import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
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

    private String address;

    //经度
    private Double longitude;
    //纬度
    private Double latitude;

    @GeoPointField
    private GeoPoint location;
}
