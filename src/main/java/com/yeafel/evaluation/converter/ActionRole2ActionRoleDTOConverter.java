package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.ActionRole;
import com.yeafel.evaluation.dto.ActionRoleDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *   权限表转换
 * Created by kangyifan on 2018/10/3 20:07
 */
public class ActionRole2ActionRoleDTOConverter {
    public static ActionRoleDTO convert(ActionRole actionRole){

        ActionRoleDTO actionRoleDTO = new ActionRoleDTO();
        BeanUtils.copyProperties(actionRole,actionRoleDTO);
        return actionRoleDTO;
    }

    public static List<ActionRoleDTO> convert(List<ActionRole> actionRoleList){
        return actionRoleList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
