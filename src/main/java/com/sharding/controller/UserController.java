package com.sharding.controller;

import com.sharding.domain.User;
import com.sharding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhs
 * @Description
 * @createTime 2020/5/30 0030 下午 3:00
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/saveUser")
    public String saveUser(String username) {
        User user = new User();
        user.setName(username);
        userService.saveUser(user);
        return "success";
    }
}
