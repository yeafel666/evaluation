package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.IndexRole;
import com.yeafel.evaluation.dto.IndexDTO;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.service.*;
import com.yeafel.evaluation.tree.ActionNode;
import com.yeafel.evaluation.tree.IndexNode;
import com.yeafel.evaluation.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.yeafel.evaluation.converter.IndexDTO2IndexNodeConverter.convert;

/**
 *      树形菜单控制层
 * Created by kangyifan on 2018/9/18 13:56
 */
@RestController
@RequestMapping("/tree")
public class TreeController {


    @Autowired
    private TreeService treeService;


    @Autowired
    private IndexTreeService indexTreeService;

    @Autowired
    private IndexRoleService indexRoleService;


    @Autowired
    private IndexService indexService;

    @GetMapping("/getTree")
    public ResultVO<String> list(HttpSession session){
        Long roleId = (Long)session.getAttribute("roleId");
        ActionNode actionNode = treeService.formTreeByRole(roleId);
        actionNode.setSpread(true);


        return ResultVOUtil.success(actionNode);
    }


    @GetMapping("/getIndexTree")
    public ResultVO<String> getIndexTree(HttpSession session){
        UserDTO userDTO = new UserDTO();
        userDTO = (UserDTO)session.getAttribute("user");
        Long roleId = (Long)session.getAttribute("roleId");
        IndexNode indexNode = indexTreeService.formTreeByRole(roleId);
        indexNode.setSpread(true);



        return ResultVOUtil.success(indexNode);
    }


    @GetMapping("/getLeafNode")
    public ResultVO<List<IndexNode>> getLeafNode(HttpSession session,Long roleId){

        /**此处代码只是在教师自评的时候起作用 .*/
        Long teacherId;
        if (roleId == null){
            roleId = (Long)session.getAttribute("roleId");
        }
        UserDTO user = (UserDTO)session.getAttribute("user");
        teacherId = user.getUserId();
        /**此处代码只是在教师自评的时候起作用 -------end.*/

        List<IndexRole> indexRoleList = indexRoleService.findIndexRolesByRoleId(roleId);

        List<IndexNode> indexNodeList = new ArrayList<>();
        for (IndexRole i : indexRoleList){          //此数组事实上只有一个元素，所以我直接开搞
            IndexDTO indexDTO = indexService.findByIndexId(i.getIndexId());
            IndexNode indexNode = convert(indexDTO);
            indexNodeList = indexTreeService.getLeafNode(indexNode);
        }


        /**
         * 此处还需要处理一下每个叶子结点的权重，到时候就不需要处理了，直接把题目的结果相加即可。
         */
        List<IndexNode> standardLeafNode = indexTreeService.getStandardLeafNode(indexNodeList);


        return ResultVOUtil.success(standardLeafNode,teacherId);
    }


}
