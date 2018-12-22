package com.yeafel.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yeafel.evaluation.dataobject.Option;
import com.yeafel.evaluation.enums.IsEffectiveEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  指标
 * Created by kangyifan on 2018/10/8 9:12
 */
@Data
public class IndexDTO {

    private Long indexId;

    /** 指标名 */
    private String indexName;

    /** 父结点 */
    private Long parentId;

    /** 权重. */
    private BigDecimal weight;

    /** 是否生效 (0、不生效)(1、生效)*/
    private Integer isEffective;

    /** 创建时间. */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /** 修改时间. */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


    /** 对应的选项.  从选项表中取*/
    private List<Option> optionList;
}
