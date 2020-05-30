package com.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 分表规则
 */
public class LocalPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {



    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> shardingValue) {
        for (String tableName : collection) {
            if (tableName.endsWith(String.valueOf(shardingValue.getValue() % 2))) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }
}
