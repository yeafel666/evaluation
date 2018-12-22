package com.yeafel.evaluation.dataobject.mapper;

import com.yeafel.evaluation.dataobject.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kangyifan on 2018/9/26 23:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUsersIfUsernameIsNotNull() {
        List<User> result = userMapper.findUsersIfUsernameIsNotNull("康一凡");
        Assert.assertNotNull(result );
    }
}