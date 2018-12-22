package com.yeafel.evaluation.service.impl;

import com.sun.xml.internal.ws.policy.AssertionSet;
import com.yeafel.evaluation.dataobject.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kangyifan on 2018/9/20 12:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    public void create() {
        Role role = new Role();
        role.setRoleName("xx角色");
        Role result = roleService.create(role);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByRoleId() {
        Role result = roleService.findByRoleId(1L);
        log.info("【查询单个角色】 result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<Role> rolePage =  roleService.findAll(request);
        Assert.assertNotEquals(0,rolePage.getTotalElements());
    }

    @Test
    public void checkUserRole() {
        Boolean result = roleService.checkUserRole(1L,1L);
        Assert.assertEquals(true,result);
    }
}