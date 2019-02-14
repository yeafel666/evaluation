package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.ChannelControl;
import com.yeafel.evaluation.dto.ChannelControlDTO;
import com.yeafel.evaluation.service.ChannelControlService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by kangyifan on 2018/10/12 23:05
 */
@RestController
@RequestMapping("/channelControl")
public class ChannelControlController {

    @Autowired
    private ChannelControlService channelControlService;

    @GetMapping("/list")
    public ResultVO<List<ChannelControl>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "limit",defaultValue = "10") Integer limit) {
        Integer count = channelControlService.count();



        PageRequest request = PageRequest.of(page-1,limit);
        Page<ChannelControlDTO> channelControlDTOPage = channelControlService.findAll(request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(channelControlDTOPage.getContent(),count);


    }


    @PostMapping("/change")
    public ResultVO<Null> change(ChannelControlDTO channelControlDTO){
        ChannelControlDTO result = channelControlService.findByChannelControlId(channelControlDTO.getChannelControlId());
        if (result.getChannelState() == 0){
            result.setChannelState(1);
        }else {
            result.setChannelState(0);
        }

        channelControlService.update(result);

        return ResultVOUtil.success();
    }

}
