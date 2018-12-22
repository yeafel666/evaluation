package com.yeafel.evaluation.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeafel.evaluation.dataobject.Option;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  指标结点
 * Created by kangyifan on 2018/10/8 20:47
 */
@Data
public class IndexNode {

    @JsonProperty("name")
    public String indexName;

    @JsonProperty("id")
    public Long indexId;

    public boolean spread = false;  //是否展开状态（默认false）

    @JsonIgnore
    public Long parentId;

    /** 权重. */
    private BigDecimal weight;

    /** 是否生效 (0、不生效)(1、生效)*/
    private Integer isEffective;

    @JsonProperty("href")
    public String url;

    @JsonProperty("children")
    public List<IndexNode> indexNodeList = new ArrayList<>();

    /** 对应的选项.  从选项表中取*/
    private List<Option> optionList;
}
