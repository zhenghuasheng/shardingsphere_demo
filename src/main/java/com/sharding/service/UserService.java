package com.sharding.service;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.sharding.domain.User;
import com.sharding.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhs
 * @Description
 * @createTime 2020/5/30 0030 下午 2:59
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;



    @Autowired
    private IdGenerator idGenerator;

    public int saveUser(User user) {
        user.setId(idGenerator.generateId().longValue());
        log.info("user id:{}", user.getId());
        return userMapper.insert(user);
    }
}
