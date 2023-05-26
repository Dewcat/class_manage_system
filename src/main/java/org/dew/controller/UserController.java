package org.dew.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.dew.mapper.UserMapper;
import org.dew.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@Api(tags = "用户鉴权接口")
@CrossOrigin("*")
@RestController
public class UserController {
    Gson gson = new Gson();
    @Autowired
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        if (userMapper.selectOne(userQueryWrapper) == null) {
            return "0";
        }
        return "1";

    }

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userMapper.insert(user);
        return "1";

    }


}
