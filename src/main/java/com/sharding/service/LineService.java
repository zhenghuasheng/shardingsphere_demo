package com.sharding.service;

import com.sharding.domain.Line;
import com.sharding.domain.Vehicle;
import com.sharding.mapper.LineRepository;
import com.sharding.mapper.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhs
 * @Description
 * @createTime 2020/6/2 0002 下午 2:59
 */

@Slf4j
@Service
public class LineService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Resource
    private LineRepository lineRepository;
    @Autowired
    private VehicleRepository vehicleRepository;



    public Line getById(Long lineId) {
        if (lineId == null) {
            return null;
        }
        Optional<Line> res = lineRepository.findById(lineId);
        return res.orElse(null);
    }



    public List<Line> queryLines(List<Long> lineIds, String keyword) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        builder.must(QueryBuilders.termsQuery("uuid", lineIds));
        builder.must(QueryBuilders.wildcardQuery("lineName", "*"+keyword+"*"));

        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page);

        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //List<Line> lines = elasticsearchTemplate.queryForList(query, Line.class);
        Page<Line> result = lineRepository.search(query);

        long total = result.getTotalElements();
        List<Line> list = result.getContent();
        log.info("queryLines result:{}, total:{}", list, total);
        return list;
    }



    @PostConstruct
    public void init() {
        //queryLines(Arrays.asList(3153178L, 3150649L, 3152639L,623344L), "99");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setLineId(1L);
        vehicle.setCode("湖南省长沙市");
        vehicleRepository.save(vehicle);

        vehicle.setId(2L);
        vehicle.setLineId(1L);
        vehicle.setCode("湖南省常德市");
        vehicleRepository.save(vehicle);
    }





}
