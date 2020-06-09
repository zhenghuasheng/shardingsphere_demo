package com.sharding.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhs
 * @Description
 * @createTime 2020/6/2 0002 下午 2:36
 */

@Data
@Document(indexName = "es_line",type = "line")
public class Line implements Serializable {

    @Id
    private Long uuid;

    private Long corpId;

    private String groupCode;

    private String groupName;

    private Long lineId;

    @Field(type = FieldType.Text, analyzer = "not_analyzed")
    private String lineCode;

    private String lineName;

    private String areaCode;

    //@Field(type = FieldType.Text, analyzer = "not_analyzed")
    private String areaName;

    private Integer lineNature;

    private Integer driveTypeCode;

    private Long lineMasterId;

    private Long lineSlaveId;

    private Double upLineMile;

    private Double downLineMile;

    private String lineUpPath;

    private String lineDownPath;

    private Integer upLineSiteCount;

    private Integer downLineSiteCount;

    private Integer lineStatus;

    private String upStartTime;

    private String upEndTime;

    private String downStartTime;

    private String downEndTime;

    private Integer planType;

    private Long creator;

    private Date creatTime;

    private Long modifier;

    private Date modifyTime;
}
