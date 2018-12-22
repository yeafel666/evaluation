package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.ChannelControl;
import com.yeafel.evaluation.dto.ChannelControlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kangyifan on 2018/10/12 22:49
 */
public interface ChannelControlService {

    Integer count();

    Page<ChannelControlDTO> findAll(Pageable pageable);


    ChannelControlDTO findByChannelControlId(Long channelControlId);

    ChannelControlDTO update(ChannelControlDTO channelControlDTO);


    ChannelControlDTO findBySemesterId(Long semesterId);
}
