package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.converter.Action2ActionNode;
import com.yeafel.evaluation.dataobject.Action;
import com.yeafel.evaluation.service.ActionService;
import com.yeafel.evaluation.service.RoleService;
import com.yeafel.evaluation.service.TreeService;
import com.yeafel.evaluation.tree.ActionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangyifan on 2018/9/23 14:09
 */
@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private ActionService actionService;

    @Autowired
    private RoleService roleService;


    /**
     *  把所有的功能都找出来放进容器中,此时是无序的。
     * @return
     */
    @Override
    public List<ActionNode> setList() {
        List<ActionNode> actionNodeList = new ArrayList<>();
        List<Action> actionList = actionService.findAll();
        for ( Action AL : actionList){
            actionNodeList.add(Action2ActionNode.convert(AL));
        }
        return actionNodeList;
    }

    /**
     * 找儿子,从某个结点开始形成一棵树
     * @param actionNode
     */
    @Override
    public ActionNode tree(ActionNode actionNode) {
        List<Action> actionList = actionService.findAll();
        List<ActionNode> actionNodeList = Action2ActionNode.convert(actionList);

        Long actionId = actionNode.actionId;

        for (ActionNode a : actionNodeList){
            if (a.parentId == actionId){                        //找儿子
                actionNode.getActionNodeList().add(a);          //找到儿子后把儿子结点放到自己儿子结点集合中
                tree(a);        //然后又找子结点的子节点，遍历下去。 当发现找不到子结点之后便会跳出for循环，这时候某条数据所展开的树就形成了
            }
        }

        return actionNode;
    }


    /**
     *  自动找父结点，从最高级父结点展开，形成整棵树(管理员专用)
     */
/*    @Override
    public ActionNode formTree() {
        ActionNode actionNode = new ActionNode();
        List<ActionNode> actionNodeList = setList();
        List<ActionNode> rootNodes = new ArrayList<>();      //用来存放根结点,如果数据库正确，只存在一个根结点
        for (ActionNode a : actionNodeList){
            boolean has = false;                        //用来标记是否有父亲
            for (ActionNode a1 : actionNodeList){           //遍历所有数据并且查看每条数据是否有父节点，如果有就标记后跳出循环
                if (a1.actionId == a.parentId){
                    has = true;
                    break;
                }
            }
            if (!has){
                rootNodes.add(a);       //把没有父结点的数据放进re容器中。	如果数据库遵循规范则只会有一个根结点。
                actionNode = tree(a);
            }
        }

        return actionNode;
    }*/



    /**
     *    根据角色形成树
     */
    @Override
    public ActionNode formTreeByRole(Long roleId) {
        ActionNode actionNode = new ActionNode();
        List<Action> actionList = roleService.findActionsByRoleId(roleId);
        List<ActionNode> actionNodeList = new ArrayList<>();
        for ( Action AL : actionList){
            actionNodeList.add(Action2ActionNode.convert(AL));      //把某个角色的所有功能放进容器中
        }
        List<ActionNode> rootNodes = new ArrayList<>();      //用来存放根结点,如果数据库正确，只存在一个根结点
        for (ActionNode a : actionNodeList){
            boolean has = false;                        //用来标记是否有父亲
            for (ActionNode a1 : actionNodeList){           //遍历所有数据并且查看每条数据是否有父节点，如果有就标记后跳出循环
                if (a1.actionId == a.parentId){
                    has = true;
                    break;
                }
            }
            if (!has){
                rootNodes.add(a);       //把没有父结点的数据放进re容器中。	如果数据库遵循规范则只会有一个根结点。
                actionNode = tree(a);
            }
        }


        return actionNode;


    }




}
