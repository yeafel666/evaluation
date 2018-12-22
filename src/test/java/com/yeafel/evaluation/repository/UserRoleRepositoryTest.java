package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by kangyifan on 2018/9/20 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void saveTest(){
        UserRole userRole = new UserRole();
        userRole.setUserId(1L);
        userRole.setRoleId( 3L);
        UserRole result = userRoleRepository.save(userRole);
        Assert.assertNotNull(result);
    }
}