package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.ChannelControl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kangyifan on 2018/10/12 22:35
 */
public interface ChannelControlRepository extends JpaRepository<ChannelControl,Long> {

    ChannelControl findByChannelControlId(Long channelControlId);

    void deleteBySemesterId(Long semesterId);

    ChannelControl findBySemesterId(Long semesterId);

}
