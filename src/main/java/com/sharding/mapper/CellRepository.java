package com.sharding.mapper;

import com.sharding.domain.CellInfo;
import com.sharding.domain.Vehicle;
import org.elasticsearch.common.Table;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhs
 * @Description
 * @createTime 2020/6/2 0002 下午 2:45
 */
public interface CellRepository extends ElasticsearchRepository<CellInfo, Long> {

}
