package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.saveUser(user);
    }

    @Test
    void testInsertNew() {
        User user = new User();
        user.setUsername("piggie2");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.queryUserById(5L);
        System.out.println("user = " + user);
    }

    @Test
    void testQueryByIds() {
        List<User> users = userMapper.queryUserByIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testQueryByIds2() {
//        List<User> users = userMapper.queryUserByIds(List.of(1L, 2L, 3L, 4L));
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateUser(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteUser(5L);
    }

    @Test
    void testQueryWrapper() {
        QueryWrapper<User> wrapper= new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out :: println);
    }

    @Test
    void testUpdateWrapper() {
        UpdateWrapper<User> userWrapper = new UpdateWrapper<User>().set("balance", 2000).eq("username", "jack");
        User newUser = new User();
        newUser.setBalance(2000);
        newUser.setUpdateTime(LocalDateTime.now());
        userMapper.update(newUser, userWrapper);
    }

    @Test
    void testUpdateWrapperDeduction() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance + 1").in("id", ids);
        userMapper.update(null, wrapper);
    }

    @Test
    void testLambdaQueryWrapper() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda()
//                .select("id", "username", "info", "balance")
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        System.out.println(users.size());
        users.forEach(System.out :: println);
    }

    @Test
    void testGroupDeduction() {
        // update t_user set balance = balance - 2 where id in 1,2,4;
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<User>()
                .in(User::getId, ids).setSql("balance = balance - 1");
        userMapper.update(null, userLambdaUpdateWrapper);
    }

    @Test
    void testCustomSqlUpdate() {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1l);
        ids.add(2l);
        ids.add(4l);
        int amount = 200;
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<User>()
                .in(User::getId, ids);
        userMapper.updateBalanceByIds(lambdaUpdateWrapper, amount);
    }
}