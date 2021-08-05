package com.sharding.service;

import com.sharding.domain.MongoCell;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhs
 * @Description
 * @createTime 2021/6/4 0004 15:26
 */
@Slf4j
@Service
public class LbsService {
    @Autowired
    private MongoCellRepository mongoCellRepository;


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2000),
            new ThreadPoolExecutor.CallerRunsPolicy());





    //@PostConstruct
    public void init() {
        //radio,mcc,net,area,cell,unit,lon,lat,range,samples,changeable,created,updated,averageSignal
        try {
            FileReader fileReader = new FileReader("D:\\install\\cell_towers.csv\\cell_towers_2021-04-07-T000000.csv");
            BufferedReader in = new BufferedReader(fileReader);

            long id = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                id ++;
                if (id < 3974465) {
                    continue;
                }
                try {
                    log.info("cell line:{}", line);
                    String[] data = line.split(",");

                    String mcc = data[1];
                    String mnc = data[2];
                    String lac = data[3];
                    String ci = data[4];
                    String lon = data[6];
                    String lat = data[7];
                    GeoJsonPoint point = new GeoJsonPoint(Double.parseDouble(lon), Double.parseDouble(lat));
                    MongoCell cell = new MongoCell();
                    cell.setCi(Long.parseLong(ci));
                    cell.setPoint(point);
                    cell.setLac(Integer.parseInt(lac));
                    cell.setMcc(Integer.parseInt(mcc));
                    cell.setMnc(Integer.parseInt(mnc));

                    cell.setId(id);
                    executor.execute(() -> mongoCellRepository.save(cell));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
