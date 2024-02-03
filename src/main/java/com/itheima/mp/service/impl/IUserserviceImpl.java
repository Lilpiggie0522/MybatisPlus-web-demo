package com.itheima.mp.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.pojo.User;
import com.itheima.mp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * ClassName: IUserserviceImpl
 * Package: com.itheima.mp.service.impl
 * Description:
 *
 * @Author Piggie
 * @Create 3/02/2024 10:39 pm
 * @Version 1.0
 */
@Service
public class IUserserviceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
