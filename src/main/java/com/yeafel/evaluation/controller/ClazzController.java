package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Clazz;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.ClazzService;
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
@RequestMapping("/clazz")
@Slf4j
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/list")
    public ResultVO<List<Clazz>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                      Clazz clazz) throws UnsupportedEncodingException {
        Integer count = clazzService.countClazzForPage(clazz.getClazzName());

        /** 此处代码作用：  从前端传回来的course值会乱码，在这里处理掉乱码问题.*/
        if (clazz.getClazzName() != null) {
            String fname =  URLDecoder.decode(clazz.getClazzName() , "utf-8");
            clazz.setClazzName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Clazz> clazzPage = clazzService.findClazzIfClazzNameIsNotNull(clazz.getClazzName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(clazzPage.getContent(),count);


    }

    //保存用户，编辑与新增公用
    @PostMapping("/save")
    public ResultVO<Null> save(Clazz clazz){

        if (clazz != null && clazz.getClazzId() != null){
            Clazz updateResult = clazzService.updateClazz(clazz);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Clazz createResult = clazzService.create(clazz);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }

    @PostMapping("/updateClazz")
    public ResultVO<Null> updateClazz(Long clazzId){

        Clazz clazz= clazzService.findByClazzId(clazzId);
        if (clazz == null){
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }

        return ResultVOUtil.success(clazz);
    }

    @GetMapping("/findByClazzId")
    public ResultVO<Clazz> findByClazzId(Long clazzId){
        Clazz clazz = clazzService.findByClazzId(clazzId);
        return ResultVOUtil.success(clazz);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                clazzService.deleteByClazzId(a);
            }
        } else {
            clazzService.deleteByClazzId(id);
        }
        return ResultVOUtil.success();
    }
}

