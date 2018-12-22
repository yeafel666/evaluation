package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dataobject.ActionRole;
import com.yeafel.evaluation.dataobject.Role;
import com.yeafel.evaluation.dto.ActionRoleDTO;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.repository.ActionRepository;
import com.yeafel.evaluation.repository.ActionRoleRepository;
import com.yeafel.evaluation.repository.RoleRepository;
import com.yeafel.evaluation.service.ActionRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.yeafel.evaluation.converter.ActionRole2ActionRoleDTOConverter.convert;

/**
 *  角色权限
 * Created by kangyifan on 2018/10/3 16:53
 */
@Service
@Transactional
public class ActionRoleServiceImpl implements ActionRoleService {

    @Autowired
    private ActionRoleRepository actionRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private ActionRepository actionRepository;

    @Override
    public List<String> findActionNamesByRoleId(Long roleId) {
        List<ActionRole> actionRoleList = actionRoleRepository.findActionRolesByRoleId(roleId);
        List<String> actionNames = new ArrayList<>();
        for (ActionRole actionRole : actionRoleList){
            actionNames.add(actionRepository.findByActionId(actionRole.getActionId()).getActionName());
        }

        return actionNames;
    }

    @Override
    public Page<ActionRoleDTO> findRoleActionIfRoleNameIsNotNull(String roleName, Pageable pageable) {
        Role role = roleRepository.findByRoleName(roleName);
        Page<ActionRole> actionRolePage = actionRoleRepository.findRoleActionIfRoleNameIsNotNull(pageable);

        List<ActionRoleDTO> actionRoleDTOList = convert(actionRolePage.getContent());
        Page<ActionRoleDTO> actionRoleDTOPage = new PageImpl<ActionRoleDTO>(actionRoleDTOList,pageable,actionRolePage.getTotalElements());
        return actionRoleDTOPage;
    }



    @Override
    public Integer countActionRoleForPage(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        Integer count = actionRoleRepository.countAllByActionRoleIdIsNotNull();

        return count;
    }

    @Override
    public ActionRoleDTO update(ActionRoleDTO actionRoleDTO) {
        ActionRoleDTO result;
        if (actionRoleDTO.getActionRoleId()!= null){

            List<ActionRole> actionRoleList = actionRoleRepository.findActionRolesByRoleId(actionRoleDTO.getRoleId());
            List<Long> ids = new ArrayList<>();         //当前角色的所有功能id
            for (ActionRole a : actionRoleList ){
                ids.add(a.getActionId());
            }

            if(actionRoleDTO.getActionIds().size() >= ids.size()){      //如果传来的比以前的多

                //1、从数据库中取出的数组中去除与传来数组相同的元素.
                List<Long> copyIds = new ArrayList<>(); //ids的副本
                for (Long id : ids){
                    copyIds.add(id);
                }
                copyIds.removeAll(actionRoleDTO.getActionIds());       //还剩下操作者不希望保留的数据,下一步删除这些数据.
                Integer count = copyIds.size();
                for (int i=0; i<count;i++){
                    actionRoleRepository.deleteByActionIdAndRoleId(copyIds.get(i),actionRoleDTO.getRoleId());   //删除操作者没选择保留的数据
                }


                actionRoleDTO.getActionIds().removeAll(ids);    //去除数组中相同的元素
                for (Long id : actionRoleDTO.getActionIds()){
                    ActionRole actionRole = new ActionRole();
                    actionRole.setActionId(id);
                    actionRole.setRoleId(actionRoleDTO.getRoleId());

                    actionRoleRepository.save(actionRole);
                }

            }else {
                List<Long> copyIds = new ArrayList<>(); //ids的副本
                for (Long id : ids){
                    copyIds.add(id);
                }
                 boolean isSuccess = copyIds.removeAll(actionRoleDTO.getActionIds());
                 if (isSuccess == false){
                     for (Long id : actionRoleDTO.getActionIds()){
                         ActionRole actionRole = new ActionRole();
                         actionRole.setRoleId(actionRoleDTO.getRoleId());
                         actionRole.setActionId(id);
                         actionRoleRepository.save(actionRole);
                     }
                     for (Long id : copyIds){
                         actionRoleRepository.deleteByActionIdAndRoleId(id,actionRoleDTO.getRoleId());
                     }
                 }else {
                     for (Long id : copyIds){
                         actionRoleRepository.deleteByActionIdAndRoleId(id,actionRoleDTO.getRoleId());
                     }
                     actionRoleDTO.getActionIds().removeAll(ids);
                     for (Long id : actionRoleDTO.getActionIds()){
                         ActionRole actionRole = new ActionRole();
                         actionRole.setActionId(id);
                         actionRole.setRoleId(actionRoleDTO.getRoleId());

                         actionRoleRepository.save(actionRole);
                     }
                 }


            }


            result = actionRoleDTO;         //只是为了result不为空
        }else {
            throw new EvaluationException(ResultEnum.ACTIONROLE_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public ActionRoleDTO create(ActionRoleDTO actionRoleDTO) {

        for (Long id : actionRoleDTO.getActionIds()){
            ActionRole actionRole = new ActionRole();
            actionRole.setActionId(id);
            actionRole.setRoleId(actionRoleDTO.getRoleId());

            actionRoleRepository.save(actionRole);
        }
        return actionRoleDTO;
    }

    @Override
    public ActionRoleDTO findByActionRoleId(Long actionRoleId) {

        ActionRole actionRole = actionRoleRepository.findByActionRoleId(actionRoleId);
        ActionRoleDTO actionRoleDTO = convert(actionRole);
        List<ActionRole> actionRoleList = actionRoleRepository.findActionRolesByRoleId(actionRoleDTO.getRoleId());

        List<Long> ids = new ArrayList<>();
        for (ActionRole a : actionRoleList){
            ids.add(a.getActionId());
        }
        actionRoleDTO.setActionIds(ids);
        return actionRoleDTO;
    }

    @Override
    public void deleteActionRolesByRoleId(Long roleId) {
         actionRoleRepository.deleteActionRolesByRoleId(roleId);
    }
}
