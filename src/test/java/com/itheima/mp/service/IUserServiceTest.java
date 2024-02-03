package com.itheima.mp.service;

import com.itheima.mp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: IUserServiceTest
 * Package: com.itheima.mp.service
 * Description:
 *
 * @Author Piggie
 * @Create 3/02/2024 10:46 pm
 * @Version 1.0
 */
@SpringBootTest
class IUserServiceTest {
    @Autowired
    private IUserService iUserService;

    @Test
    void testInsert() {
        User user = new User();
        user.setUsername("piggie");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        iUserService.save(user);
    }

    @Test
    void testQuery() {
        List<User> users = iUserService.listByIds(List.of(1, 2, 3));
        users.forEach(System.out :: println);
    }
}