package com.sharding.domain;

import lombok.Data;

/**
 * @author zhs
 * @Description
 * @createTime 2020/5/30 0030 下午 2:39
 */
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
