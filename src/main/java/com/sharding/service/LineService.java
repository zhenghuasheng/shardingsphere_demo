package com.sharding.service;

import com.sharding.domain.CellInfo;
import com.sharding.domain.Line;
import com.sharding.domain.Vehicle;
import com.sharding.domain.WifiInfo;
import com.sharding.mapper.CellRepository;
import com.sharding.mapper.LineRepository;
import com.sharding.mapper.VehicleRepository;
import com.sharding.mapper.WifiRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
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

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private WifiRepository wifiRepository;



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

        // 排序
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //List<Line> lines = elasticsearchTemplate.queryForList(query, Line.class);
        Page<Line> result = lineRepository.search(query);

        long total = result.getTotalElements();
        List<Line> list = result.getContent();
        log.info("queryLines result:{}, total:{}", list, total);
        return list;
    }



    //@PostConstruct
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

        vehicle.setId(2L);
        vehicle.setLineId(1L);
        vehicle.setCode("湖南省常德市鼎城区");
        vehicleRepository.save(vehicle);

//        CellInfo cellInfo = new CellInfo();
//        cellInfo.setCellId(10000L);
//        cellInfo.setAddress("长沙西站高铁站");
//        cellInfo.setLongitude(112.82182525579599);
//        cellInfo.setLatitude(28.2660692542112);
//
//        GeoPoint point = new GeoPoint(cellInfo.getLatitude(), cellInfo.getLongitude());
//        cellInfo.setLocation(point);
//        cellRepository.save(cellInfo);
//
//
//        CellInfo cellInfo2 = new CellInfo();
//        cellInfo2.setCellId(10001L);
//        cellInfo2.setAddress("长沙麓谷站");
//        cellInfo2.setLongitude(112.85925366711686);
//        cellInfo2.setLatitude(28.25100765328863);
//        GeoPoint point2 = new GeoPoint(cellInfo2.getLatitude(), cellInfo2.getLongitude());
//        cellInfo2.setLocation(point2);
//        cellRepository.save(cellInfo2);

//        Page<CellInfo> result = getNearCellList(28.2511d, 112.8592d, 5d);
//        System.out.println(result);
//        result.forEach(cellInfo -> {
//            double distance = GeoDistance.ARC.calculate(28.2511d, 112.8592d, cellInfo.getLocation().getLat(),
//                    cellInfo.getLocation().getLon(), DistanceUnit.KILOMETERS);
//            System.out.println("; 距离我 : "+distance+"公里");
//        });


//        Page<WifiInfo> result = getNearWifiList(28.206d, 113.006d, 1d);
//        System.out.println(result);
//        result.forEach(cellInfo -> {
//            double distance = GeoDistance.ARC.calculate(28.206d, 113.006d, cellInfo.getLocation().getLat(),
//                    cellInfo.getLocation().getLon(), DistanceUnit.KILOMETERS);
//            System.out.println("; 距离我 : "+distance+"公里");
//        });
    }


    public Page<WifiInfo> getNearWifiList(Double lat, Double lon, Double distance) {
        // 实现了SearchQuery接口，用于组装QueryBuilder和SortBuilder以及Pageable等
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 分页
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 200);
        nativeSearchQueryBuilder.withPageable(page);

        // 间接实现了QueryBuilder接口
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // 以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(lat, lon);
        // 定义查询单位：公里
        distanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);

        // 按距离升序
        GeoDistanceSortBuilder distanceSortBuilder =
                new GeoDistanceSortBuilder("location", lat, lon);
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.ASC);
        nativeSearchQueryBuilder.withSort(distanceSortBuilder);
        return wifiRepository.search(nativeSearchQueryBuilder.build());
    }

    public Page<CellInfo> getNearCellList(Double lat, Double lon, Double distance) {
        // 实现了SearchQuery接口，用于组装QueryBuilder和SortBuilder以及Pageable等
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 分页
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page = PageRequest.of(0, 10);
        nativeSearchQueryBuilder.withPageable(page);

        // 间接实现了QueryBuilder接口
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        // 以某点为中心，搜索指定范围
        GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
        distanceQueryBuilder.point(lat, lon);
        // 定义查询单位：公里
        distanceQueryBuilder.distance(distance, DistanceUnit.KILOMETERS);
        boolQueryBuilder.filter(distanceQueryBuilder);
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);

        // 按距离升序
        GeoDistanceSortBuilder distanceSortBuilder =
                new GeoDistanceSortBuilder("location", lat, lon);
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.DESC);
        nativeSearchQueryBuilder.withSort(distanceSortBuilder);
        return cellRepository.search(nativeSearchQueryBuilder.build());
    }
}
