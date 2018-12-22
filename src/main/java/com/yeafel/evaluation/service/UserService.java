package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.Department;
import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kangyifan on 2018/9/13 12:32
 */
public interface UserService {

    /** 创建用户. */
    UserDTO create(UserDTO userDTO);

    /** 查询单个用户. */
    UserDTO findByUserId(Long userId);

    /** 根据人员编号查询用户. */
    UserDTO findByUserNo(String userNo);

    /** 查找所有用户. */
    Page<UserDTO> findAll(Pageable pageable);

    /** 服从Layui查询接口 .*/
    Page<UserDTO> findUsersIfUsernameIsNotNull(String username,Pageable pageable);

    /**  根据身份查所有用户 . */
    Page<UserDTO> findAllByIdentity(Integer identity, Pageable pageable);

    /** 根据身份和部门查找用户 */
    Page<UserDTO> findAllByIdentityAndDepartmentNo(Integer identity,String departmentNo,Pageable pageable);

    /** 根据班级编号查找用户 */
    Page<UserDTO> findAllByClazzNo(String clazzNo,Pageable pageable);

    /** 计算用户总数 */
    Integer countUsersByUserIdIsNotNull();

    /**   修改用户信息. */
    UserDTO updateUser(UserDTO userDTO);


    /** 删除用户. */
    void deleteByUserId(Long userId);


    /** 为了分页查询总数 .*/
    Integer countUserForPage(String username);


    /** 根据院系编号查找院系. */
    Department getByDepartmentNo(String departmentNo);


    /** 查找所有老师. */
    Page<User> findTeachers(String username,Pageable pageable);

    /** 查询老师的数量. */
    Integer countTeachers(String username,Pageable pageable);


    /** 根据姓名查找用户 .*/
    UserDTO findByUsername(String username);


    /** 为了提供通过id进行条件查询并分页 。*/
    Page<UserDTO> findIfUserId(Long userId,Pageable pageable);


    Integer countIfUserId(Long userId);
    /** 为了提供通过id进行条件查询并分页 。-----end*/


    /** 根据不同的角色评教 */
//    void evaluate(Integer userId,)


    /** 根据不同的角色查看评教结果.*/
}
