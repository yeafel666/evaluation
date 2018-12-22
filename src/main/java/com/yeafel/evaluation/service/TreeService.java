package com.yeafel.evaluation.service;

import com.yeafel.evaluation.tree.ActionNode;

import java.util.List;

/**
 *  关于树形菜单
 * Created by kangyifan on 2018/9/23 14:04
 */
public interface TreeService {

    //把所有的功能都找出来放进容器中,此时是无序的。
    List<ActionNode> setList();

    //找儿子,从某个结点开始形成一棵树
    ActionNode tree(ActionNode actionNode);

    //自动找父结点，从最高级父结点展开，形成整棵树
//    ActionNode formTree();

    //根据角色形成树
    ActionNode formTreeByRole(Long roleId);



}
