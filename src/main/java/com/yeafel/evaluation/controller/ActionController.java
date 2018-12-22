package com.yeafel.evaluation.controller;


import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Action;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.ActionService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/action")
@Slf4j
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping("/list")
    public ResultVO<List<Action>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                      Action action) throws UnsupportedEncodingException {
        Integer count = actionService.countActionForPage(action.getActionName());

        /** 此处代码作用：  从前端传回来的course值会乱码，在这里处理掉乱码问题.*/
        if (action.getActionName() != null) {
            String fname =  URLDecoder.decode(action.getActionName() , "utf-8");
            action.setActionName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Action> actionPage = actionService.findActionIfActionNameIsNotNull(action.getActionName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(actionPage.getContent(),count);


    }

    @GetMapping("/findByActionId")
    public ResultVO<Action> findByActionId(Long actionId){
       Action action =  actionService.findByActionId(actionId);
        return ResultVOUtil.success(action);
    }


    @PostMapping("/save")
    public ResultVO<Null> save(Action action){

        if (action != null && action.getActionId() != null){
            Action updateResult = actionService.update(action);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Action createResult = actionService.create(action);
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
                actionService.delete(a);
            }
        } else {
            actionService.delete(id);
        }
        return ResultVOUtil.success();
    }


}
