package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.dto.IndexDTO;
import com.yeafel.evaluation.service.IndexService;
import com.yeafel.evaluation.tree.IndexNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.yeafel.evaluation.converter.IndexDTO2IndexNodeConverter.convert;
import static org.junit.Assert.*;

/**
 * Created by kangyifan on 2018/10/16 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IndexTreeServiceImplTest {

    @Autowired
    private IndexTreeServiceImpl indexTreeService;

    @Autowired
    private IndexService indexService;


    @Test
    public void getParentNode() {
        IndexDTO indexDTO = indexService.findByIndexId(18L);
        List<IndexNode> indexNodeList =indexTreeService.getParentNode(convert(indexDTO));
        Assert.assertNotNull(indexNodeList);
    }

    @Test
    public void getLeafNode() {
        IndexDTO indexDTO =indexService.findByIndexId(27L);
        List<IndexNode> result = indexTreeService.getLeafNode(convert(indexDTO));
        Assert.assertNotNull(result);

    }
}