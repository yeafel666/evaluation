package com.yeafel.evaluation.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *  功能结点
 * Created by kangyifan on 2018/9/23 2:42
 */

@Data
public class ActionNode {

    @JsonProperty("name")
    public String actionName;

    @JsonProperty("id")
    public Long actionId;

    public boolean spread = false;  //是否展开状态（默认false）

    @JsonIgnore
    public Long parentId;

    @JsonProperty("href")
    public String url;

    @JsonProperty("children")
    public List<ActionNode> actionNodeList = new ArrayList<>();


}
