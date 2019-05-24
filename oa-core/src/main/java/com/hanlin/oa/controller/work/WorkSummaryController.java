package com.hanlin.oa.controller.work;

import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.DTO.UserSummaryDto;
import com.hanlin.oa.model.VO.SummaryEditVo;
import com.hanlin.oa.model.VO.SummaryVo;
import com.hanlin.oa.model.VO.UserSummaryVo;
import com.hanlin.oa.model.domain.WorkSummary;
import com.hanlin.oa.service.WorkSummaryService;
import org.hibernate.validator.constraints.URL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/21 15:21
 * @Modified By:
 */
@RestController
@RequestMapping("work")
public class WorkSummaryController extends BaseController {

    @Autowired
    private WorkSummaryService workSummaryService;

    @GetMapping("")
    public Wrapper list(){
        return WrapMapper.ok();
    }

    @PostMapping("save")
    public Wrapper save(WorkSummary workSummary){
        workSummaryService.save(workSummary);
        return WrapMapper.ok();
    }

    @GetMapping("mySummary")
    public Object mySummary(String start ,String end){
        List<SummaryVo> list = workSummaryService.selectMySummaryVo(start,end);
        return list;
    }

    @GetMapping("getSummary")
    public Wrapper getSummary(Long id){
        WorkSummary workSummary = workSummaryService.selectByPrimaryKey(id);
        return WrapMapper.ok(new ModelMapper().map(workSummary, SummaryEditVo.class));
    }

    @GetMapping("userSummary")
    public Wrapper userSummary(UserSummaryDto userSummaryDto){
        PageInfo pageinfo = workSummaryService.selectUserSummary(initPage(),userSummaryDto);
        return WrapMapper.ok(pageinfo);
    }

    @PostMapping("del")
    public Wrapper del(Long id){
        return workSummaryService.deleteByPrimaryKey(id) > 0 ? WrapMapper.ok():WrapMapper.error();
    }
}
