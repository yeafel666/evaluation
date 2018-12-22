package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.converter.Index2IndexNodeConverter;
import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.dataobject.Option;
import com.yeafel.evaluation.service.IndexService;
import com.yeafel.evaluation.service.IndexTreeService;
import com.yeafel.evaluation.service.OptionService;
import com.yeafel.evaluation.service.RoleService;
import com.yeafel.evaluation.tree.IndexNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.yeafel.evaluation.converter.Index2IndexNodeConverter.convert;

/**
 * Created by kangyifan on 2018/10/8 21:02
 */
@Service
@Transactional
public class IndexTreeServiceImpl implements IndexTreeService {

    @Autowired
    private IndexService indexService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OptionService optionService;

    private static List<IndexNode> parentNodes = new ArrayList<>();


    private static List<IndexNode> leafNodeList = new ArrayList<>();        //装叶子结点，因为只有叶子结点才会作为题目


    /**
     * 找儿子,从某个结点开始形成一棵树
     * @param indexNode
     */
    @Override
    public IndexNode tree(IndexNode indexNode) {
        List<Index> indexList = indexService.findAll();
        List<IndexNode> indexNodeList = convert(indexList);

        Long indexId = indexNode.indexId;

        for (IndexNode a : indexNodeList){
            if (a.parentId == indexId){                        //找儿子
                if (optionService.findOptionsByIndexId(a.indexId).size() != 0){
                    a.setOptionList(optionService.findOptionsByIndexId(a.indexId) );
                }
                if (!indexNode.getIndexNodeList().contains(a)){
                    indexNode.getIndexNodeList().add(a);          //找到儿子后把儿子结点放到自己儿子结点集合中
                }
                tree(a);        //然后又找子结点的子节点，遍历下去。 当发现找不到子结点之后便会跳出for循环，这时候某条数据所展开的树就形成了
            }
        }

        return indexNode;
    }


    /** 收集所有叶子结点
     *
     * @param indexNode
     * @return
     */
    @Override
    public void putLeafNode(IndexNode indexNode) {

        IndexNode indexNodeTree = tree(indexNode);


//         List<IndexNode> leafNodeList = new ArrayList<>();        //装叶子结点，因为只有叶子结点才会作为题目      (已经搞成了全局静态变量)
        List<IndexNode> childList = indexNodeTree.getIndexNodeList();
        if (childList.size() != 0){
            for (IndexNode i : childList){
                putLeafNode(i);
            }

        }else {
            leafNodeList.add(indexNode);

        }


    }

    @Override
    public List<IndexNode> getLeafNode(IndexNode indexNode) {
        leafNodeList.clear();
        putLeafNode(indexNode);
        return leafNodeList;
    }


    /**
     * 处理叶子结点的权重，形成最终叶子结点。
     * @param indexNodeList
     * @return
     */
    @Override
    public List<IndexNode> getStandardLeafNode(List<IndexNode> indexNodeList) {

       for (IndexNode indexNode : indexNodeList){
           List<IndexNode> parentNodeList = getParentNode(indexNode);
           for (IndexNode i : parentNodeList ){
               indexNode.setWeight(indexNode.getWeight().multiply(i.getWeight()));
           }
           parentNodes.clear();     //对于全局静态变量一定要谨慎
       }

        return indexNodeList;
    }


    /** 寻找父节点 */
    @Override
    public List<IndexNode> getParentNode(IndexNode indexNode) {
        List<Index> indexList = indexService.findAll();
        List<IndexNode> allIndexNodeList = convert(indexList);    //把所有指标放进容器中
//        List<IndexNode> parentNodes = new ArrayList<>();        //用来装所有的父节点   此变量需要声明成静态变量
        for (IndexNode i : allIndexNodeList){
            if (indexNode.getParentId() == i.getIndexId()){        //找到了父节点
                parentNodes.add(i);
                getParentNode(i);
            }
        }
        return parentNodes;
    }


    /**
     * @param roleId
     * @return
     */
    @Override
    public IndexNode formTreeByRole(Long roleId) {
        IndexNode indexNode = new IndexNode();
        List<Index> indexList = roleService.findIndexesByRoleId(roleId);
        List<IndexNode> indexNodeList = new ArrayList<>();
        for ( Index index : indexList){
            indexNodeList.add(convert(index));      //把某个角色的所有指标放进容器中
        }
        List<IndexNode> rootNodes = new ArrayList<>();      //用来存放根结点,如果数据库正确，只存在一个根结点
        for (IndexNode i : indexNodeList){
            boolean has = false;                        //用来标记是否有父亲
            for (IndexNode a1 : indexNodeList){           //遍历所有数据并且查看每条数据是否有父节点，如果有就标记后跳出循环
                if (a1.indexId == i.parentId){
                    has = true;
                    break;
                }
            }
            if (!has){
                rootNodes.add(i);       //把没有父结点的数据放进re容器中。	如果数据库遵循规范则只会有一个根结点。
                indexNode = tree(i);
            }
        }


        return indexNode;
    }
}
