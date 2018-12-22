package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 评教控制表
 * Created by kangyifan on 2018/9/13 11:33
 */
@Data
@Entity
public class ChannelControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelControlId;


    private Long semesterId;


    /**  通道状态    0、未开启   1、已开启  */
    private Integer channelState;
}
