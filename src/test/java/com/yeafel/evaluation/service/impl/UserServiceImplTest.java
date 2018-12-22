package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.enums.IdentityEnum;
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
 * Created by kangyifan on 2018/9/14 9:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void create() throws Exception{

        UserDTO userDTO = new UserDTO();

        userDTO.setUserNo("159000402");
        userDTO.setPassword("123456");
        userDTO.setUsername("谭富宝");
        userDTO.setClazzNo("1590004");
        userDTO.setDepartmentNo("001");
        userDTO.setIdentity(IdentityEnum.STUDENT.getCode());
        UserDTO result = userService.create(userDTO);
        Assert.assertNotNull(result);

    }


    @Test
    public void findByUserId() throws Exception {
        UserDTO result = userService.findByUserId(1L);
        log.info("【查询单个用户】 result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() throws  Exception{
        PageRequest request = PageRequest.of(0,2);
        Page<UserDTO> userDTOPage = userService.findAll(request);
        Assert.assertNotEquals(0,userDTOPage.getTotalElements());
    }

    @Test
    public void findAllByIdentity() throws Exception{
        PageRequest request = PageRequest.of(0,2);
        Page<UserDTO> userDTOPage = userService.findAllByIdentity(IdentityEnum.STUDENT.getCode(),request);
        Assert.assertNotEquals(0,userDTOPage.getTotalElements());
    }

    @Test
    public void findAllByIdentityAndDepartmentNo() throws Exception{
    }

    @Test
    public void findAllByClazzNo() throws Exception{
    }


    @Test
    public void countUsersByUserIdIsNotNull()  throws  Exception{
        Integer count = userService.countUsersByUserIdIsNotNull();
        Assert.assertNotNull(count);
    }

    @Test
    public void findByUserNo() throws Exception {
        UserDTO userDTO = userService.findByUserNo("159000425");
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void updateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setPassword("123123");
        userService.updateUser(userDTO);
    }

    @Test
    public void findUsersIfUsernameIsNotNull() {
        PageRequest request = PageRequest.of(0,2);
        Page<UserDTO> userDTOPage = userService.findUsersIfUsernameIsNotNull(null,request);

        Assert.assertNotEquals(0,userDTOPage.getTotalElements());

    }
}