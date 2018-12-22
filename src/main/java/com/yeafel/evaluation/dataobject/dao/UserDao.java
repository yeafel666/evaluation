package com.yeafel.evaluation.dataobject.dao;

import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dataobject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *         整个项目我没有用mybatis，此处不做删除操作
 * Created by kangyifan on 2018/9/27 0:15
 */
@Repository
public class UserDao{

    @Autowired
    UserMapper mapper;

    public List<User> findUsersIfUsernameIsNotNull(String username){
        return mapper.findUsersIfUsernameIsNotNull(username);
    }
}
