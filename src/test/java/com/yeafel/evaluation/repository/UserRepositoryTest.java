package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.enums.IdentityEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by kangyifan on 2018/9/13 12:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;


    @Test
    public void saveTest(){

        User user = new User();
        user.setUserNo("159000411");
        user.setPassword("123456");
        user.setUsername("谭富宝");
        user.setClazzNo("1590004");
        user.setDepartmentNo("001");
        user.setIdentity(IdentityEnum.STUDENT.getCode());


        User result = repository.save(user);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByUserId() {
        User result = repository.findByUserId(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllByIdentity() throws Exception{
        PageRequest request = PageRequest.of(0,2);
        Page<User> userPage = repository.findAllByIdentity(IdentityEnum.STUDENT.getCode(),request);
        Assert.assertNotEquals(0,userPage.getTotalElements());
    }

    @Test
    public void findAllByIdentityAndDepartmentNo() {
        PageRequest request = PageRequest.of(0,2);
        Page<User> userPage = repository.findAllByIdentityAndDepartmentNo(IdentityEnum.STUDENT.getCode(),"001",request);
        Assert.assertNotEquals(0,userPage.getTotalElements());
    }

    @Test
    public void findAllByClazzNo() {
        PageRequest request = PageRequest.of(0,2);
        Page<User> userPage = repository.findAllByClazzNo("1590004",request);
        assertNotEquals(0,userPage.getTotalElements());
    }
}