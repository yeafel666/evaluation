package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dataobject.Action;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by kangyifan on 2018/9/18 16:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ActionServiceImplTest {

    @Autowired
    private ActionServiceImpl actionService;

    @Test
    public void create() throws Exception{

        Action action = new Action();
        action.setActionName("xx功能");
        action.setUrl("xxxx");
        action.setParentId(0L);
        Action result = actionService.create(action);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() throws Exception{
        actionService.delete(4L);
    }

    @Test
    public void findByActionId() {
        Action action = actionService.findByActionId(3L);
        Assert.assertNotNull(action);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<Action> actionPage = actionService.findAll(request);
        Assert.assertNotEquals(0,actionPage.getTotalElements());
    }
}