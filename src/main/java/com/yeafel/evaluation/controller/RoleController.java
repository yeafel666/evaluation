package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.Role;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.RoleService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by kangyifan on 2018/9/29 10:51
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResultVO<List<Role>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                     Role role) throws UnsupportedEncodingException {
        Integer count = roleService.countRoleForPage(role.getRoleName());

        /** 此处代码作用：  从前端传回来的值会乱码，在这里处理掉乱码问题.*/
        if (role.getRoleName() != null) {
            String fname =  URLDecoder.decode(role.getRoleName() , "utf-8");
            role.setRoleName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Role> rolePage = roleService.finRolesIfRolenameIsNotNull(role.getRoleName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(rolePage.getContent(),count);


    }


    @GetMapping("/findByRoleId")
    public ResultVO<Role> findByRoleId(Long roleId){

        Role role = roleService.findByRoleId(roleId);

        return ResultVOUtil.success(role);
    }


    //保存角色，编辑与新增公用
    @PostMapping("/save")
    public ResultVO<Null> save(Role role){

        if (role != null && role.getRoleId() != null){
            Role updateResult = roleService.updateRole(role);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Role createResult = roleService.create(role);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }


    //删除角色
    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                roleService.deleteByRoleId(a);
            }
        } else {
            roleService.deleteByRoleId(id);
        }
        return ResultVOUtil.success();
    }

}
