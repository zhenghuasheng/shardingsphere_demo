package com.sharding.domain;

import lombok.Data;
import org.elasticsearch.index.Index;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author zhs
 * @Description
 * @createTime 2020/6/2 0002 下午 6:24
 */
@Data
@Document(indexName = "vehicle_data",type = "vehicle")
public class Vehicle implements Serializable {
    @Id
    private Long id;

    @Field(type = FieldType.Text,  analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String code;


    private Long lineId;
}
