package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.ChannelControl;
import com.yeafel.evaluation.dto.ChannelControlDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  通道
 * Created by kangyifan on 2018/10/12 23:19
 */
public class ChannelControl2ChannelControlDTOConverter {

    public static ChannelControlDTO convert(ChannelControl channelControl){

        ChannelControlDTO channelControlDTO = new ChannelControlDTO();
        BeanUtils.copyProperties(channelControl,channelControlDTO);
        return channelControlDTO;
    }

    public static List<ChannelControlDTO> convert(List<ChannelControl> channelControlList){
        return channelControlList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
