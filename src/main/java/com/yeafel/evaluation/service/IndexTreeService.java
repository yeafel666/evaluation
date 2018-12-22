package com.yeafel.evaluation.service;

import com.yeafel.evaluation.tree.IndexNode;

import java.util.List;

/**
 * Created by kangyifan on 2018/10/8 20:55
 */
public interface IndexTreeService {

    /** 找儿子,从某个结点开始形成一棵树 */
     IndexNode tree(IndexNode indexNode);

    //根据角色形成树
    IndexNode formTreeByRole(Long roleId);

    //把叶子结点放到容器中(递归)
    void putLeafNode(IndexNode indexNode);

    //得到所有的叶子结点。(调用)
    List<IndexNode> getLeafNode(IndexNode indexNode);


    List<IndexNode> getStandardLeafNode(List<IndexNode> indexNodeList);


    List<IndexNode> getParentNode(IndexNode indexNode);
}
