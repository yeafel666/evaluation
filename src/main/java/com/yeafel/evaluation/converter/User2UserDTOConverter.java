package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  用户类型转换
 * Created by kangyifan on 2018/9/17 16:20
 */
public class User2UserDTOConverter {

    public static UserDTO convert(User user){

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static List<UserDTO> convert(List<User> userList){
        return userList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }

}
