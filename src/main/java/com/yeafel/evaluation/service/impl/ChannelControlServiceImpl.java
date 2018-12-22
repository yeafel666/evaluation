package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.converter.ChannelControl2ChannelControlDTOConverter;
import com.yeafel.evaluation.dataobject.ChannelControl;
import com.yeafel.evaluation.dto.ChannelControlDTO;
import com.yeafel.evaluation.repository.ChannelControlRepository;
import com.yeafel.evaluation.repository.SemesterRepository;
import com.yeafel.evaluation.service.ChannelControlService;
import com.yeafel.evaluation.service.SemesterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.evaluation.converter.ChannelControl2ChannelControlDTOConverter.convert;

/**
 * Created by kangyifan on 2018/10/12 22:53
 */
@Service
@Transactional
public class ChannelControlServiceImpl implements ChannelControlService {

    @Autowired
    private ChannelControlRepository channelControlRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Integer count() {
        Integer count =  new Long(channelControlRepository.count()).intValue();
        return count;
    }

    @Override
    public Page<ChannelControlDTO> findAll(Pageable pageable) {
        Page<ChannelControl> channelControlPage = channelControlRepository.findAll(pageable);
        List<ChannelControl> channelControlList = channelControlPage.getContent();
        List<ChannelControlDTO> channelControlDTOList = convert(channelControlList);
        for (ChannelControlDTO channelControlDTO : channelControlDTOList){
            channelControlDTO.setSemesterName(semesterRepository.findBySemesterId(channelControlDTO.getSemesterId()).getSemesterName());
        }
        Page<ChannelControlDTO> channelControlDTOPage = new PageImpl<ChannelControlDTO>(channelControlDTOList,pageable,channelControlPage.getTotalElements());
        return channelControlDTOPage;
    }

    @Override
    public ChannelControlDTO findByChannelControlId(Long channelControlId) {
        ChannelControl channelControl = channelControlRepository.findByChannelControlId(channelControlId);
        ChannelControlDTO channelControlDTO = convert(channelControl);
        return channelControlDTO;
    }

    @Override
    public ChannelControlDTO update(ChannelControlDTO channelControlDTO) {
        ChannelControl channelControl = new ChannelControl();
        BeanUtils.copyProperties(channelControlDTO,channelControl);
        ChannelControl result = channelControlRepository.save(channelControl);
        return convert(result);
    }

    @Override
    public ChannelControlDTO findBySemesterId(Long semesterId) {
        ChannelControl channelControl  = channelControlRepository.findBySemesterId(semesterId);
        return convert(channelControl);
    }
}
