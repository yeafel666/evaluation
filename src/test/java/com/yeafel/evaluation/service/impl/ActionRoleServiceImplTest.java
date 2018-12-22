package com.yeafel.evaluation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kangyifan on 2018/10/3 22:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActionRoleServiceImplTest {

    @Autowired
    private ActionRoleServiceImpl actionRoleService;

    @Test
    public void findActionNamesByRoleId() {

        List<String> result = actionRoleService.findActionNamesByRoleId(1L);
        Assert.assertNotEquals(0,result.size());
    }
}