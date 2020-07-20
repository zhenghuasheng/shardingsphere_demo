package com.sharding.service;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.sharding.domain.Line;
import com.sharding.domain.User;
import com.sharding.mapper.LineRepository;
import com.sharding.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhs
 * @Description
 * @createTime 2020/5/30 0030 下午 2:59
 */

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;



    @Autowired
    private IdGenerator idGenerator;



    public int saveUser(User user) {
        user.setId(idGenerator.generateId().longValue());
        log.info("user id:{}", user.getId());
        return userMapper.insert(user);
    }



    @Cacheable(cacheNames = "user", keyGenerator = "localKeyGenerator")
    public User getUserInfo() {
        User user = new User();
        user.setId(1L);
        user.setName("zhenghuasheng");
        return user;
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public List<User> getUserList() {
        Object obj = redisTemplate.opsForValue().get("userList");
        List<User> list = new ArrayList<>();
        if (obj == null) {
            User user = new User();
            user.setId(1L);
            user.setName("zhenghuasheng");
            list.add(user);

            User user2 = new User();
            user2.setId(2L);
            user2.setName("jcx");
            list.add(user2);

            redisTemplate.opsForValue().set("userList", list);
            return list;
        }
        list = (List<User>) obj;
        return list;

    }




}
