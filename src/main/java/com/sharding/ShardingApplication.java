package com.sharding;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@SpringBootApplication
@MapperScan("com.sharding.mapper")
public class ShardingApplication {

    @Bean
    public IdGenerator getIdGenerator() {
        return new CommonSelfIdGenerator();
    }

    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }
}

