package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Option;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.OptionService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.List;

/**
 *  指标选项
 * Created by kangyifan on 2018/10/9 13:56
 */
@RestController
@RequestMapping("/option")
@Slf4j
public class OptionController {

    @Autowired
    private OptionService optionService;


    /** 为layui不分页查询.*/
    @GetMapping("/listNotPage")
    public ResultVO<List<Option>> list(Option option) {

        Integer count = optionService.countRoleForPage(option.getIndexId());

        PageRequest request = PageRequest.of(0,9999);       //为了不分页查出所有数据。
        Page<Option> optionPage  = optionService.findOptionsByIndexId(option.getIndexId(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(optionPage.getContent(),count);


    }

    @GetMapping("/findOptionByOptionId")
    public ResultVO<List<Option>> findOptionByIndexId(Long optionId){

        if (optionId != null){
            Option option = optionService.findOptionByOptionId(optionId);
            return ResultVOUtil.success(option);

        }else {
            throw new EvaluationException(ResultEnum.PARAM_ERROR);
        }

    }


    @PostMapping("/save")
    public ResultVO<Null> save(Option option){

        if (option != null && option.getOptionId() != null){
            Option updateResult = optionService.update(option);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Option createResult = optionService.create(option);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                optionService.delete(a);
            }
        } else {
            optionService.delete(id);
        }
        return ResultVOUtil.success();
    }

}
