package com.yeafel.evaluation.dto;

import lombok.Data;

/**
 * Created by kangyifan on 2018/10/12 23:14
 */
@Data
public class ChannelControlDTO {

    private Long channelControlId;


    private Long semesterId;


    private String semesterName;


    /**  通道状态    0、未开启   1、已开启  */
    private Integer channelState;


}
