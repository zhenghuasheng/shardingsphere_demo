package com.sharding.mapper;

import com.sharding.domain.Vehicle;
import com.sharding.domain.WifiInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhs
 * @Description
 * @createTime 2021/3/10 0010 14:10
 */
public interface WifiRepository extends ElasticsearchRepository<WifiInfo, String> {
}
