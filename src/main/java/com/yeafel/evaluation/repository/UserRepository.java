package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kangyifan on 2018/9/13 11:54
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /** 根据id查询用户. */
    User findByUserId(Long UserId);

    /** 根据人员编号查询用户. */
    User findByUserNo(String userNo);

    /**  根据身份查所有用户 . */
    Page<User> findAllByIdentity(Integer identity, Pageable pageable);

    /** 根据身份和部门查找用户 */
    Page<User> findAllByIdentityAndDepartmentNo(Integer identity,String departmentNo,Pageable pageable);

    /** 根据班级编号查找用户 */
    Page<User> findAllByClazzNo(String clazzNo, Pageable pageable);

    /** 计算总用户数 */
    Integer countUsersByUserIdIsNotNull();

    /** 删除用户 */
    void deleteByUserId(Long userId);


    @Transactional
    @Query(value = "select * from user where if(?1 !='',username=?1,1=1)",nativeQuery = true)
    Page<User> findUsersIfUsernameIsNotNull(String username,Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from user where if(?1 !='',username=?1,1=1)",nativeQuery = true)
    Integer countUserForPage(String username);


    /** 为了提供通过id进行条件查询并分页 。*/
    @Transactional
    @Query(value = "select * from user where if(?1 !='',user_id=?1,1=1)",nativeQuery = true)
    Page<User> findIfUserId(Long userId,Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from user where if(?1 !='',user_id=?1,1=1)",nativeQuery = true)
    Integer countIfUserId(Long userId);
    /** 为了提供通过id进行条件查询并分页 。-----end*/



    @Query(value = "select * from user where identity =1 and if(?1 !='',username=?1,1=1)",nativeQuery = true)
    Page<User> findTeachers(String username,Pageable pageable);


    User findByUsername(String username);



/*    @Transactional
    @Modifying
    @Query(value = "delete from Account where id =?1",nativeQuery = true)*/


}
