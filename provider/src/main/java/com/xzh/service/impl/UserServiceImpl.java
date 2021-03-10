package com.xzh.service.impl;

import com.xzh.service.UserService;

/**
 * @author 向振华
 * @date 2021/03/10 13:45
 */
public class UserServiceImpl implements UserService {

    public String getUserNameById(Long userId) {
        return userId > 0 ? "向振华" : "无名";
    }
}
