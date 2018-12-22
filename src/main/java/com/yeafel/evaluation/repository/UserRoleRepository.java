package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kangyifan on 2018/9/20 14:24
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{

    /** 通过userId查询该用户所拥有的所有角色.  */
      List<UserRole> findUserRolesByUserId(Long userId);

}
