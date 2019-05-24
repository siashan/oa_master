package com.hanlin.oa.service;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hanlin.oa.common.service.BaseService;
import com.hanlin.oa.common.support.orm.Page;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.mapper.WorkSummaryMapper;
import com.hanlin.oa.model.DTO.UserSummaryDto;
import com.hanlin.oa.model.VO.SummaryTreeVo;
import com.hanlin.oa.model.VO.SummaryVo;
import com.hanlin.oa.model.VO.UserSummaryVo;
import com.hanlin.oa.model.domain.UacUser;
import com.hanlin.oa.model.domain.WorkSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/21 15:20
 * @Modified By:
 */
@Service
public class WorkSummaryService extends BaseService<WorkSummary> {
    @Autowired
    private WorkSummaryMapper workSummaryMapper;
    @Autowired
    private UacUserService uacUserService;


    public void save(WorkSummary workSummary) {
        if (null == workSummary.getId()){
            long uaerId = SecurityUtils.getCurrentUserId();
            workSummary.setUserId(uaerId);
            workSummary.setCreateTime(new Date());
            workSummaryMapper.insertSelective(workSummary);
        }else{
            workSummaryMapper.updateByPrimaryKeySelective(workSummary);
        }

    }

    public List<WorkSummary> selectMySummary(String start, String end) {
        return workSummaryMapper.selectMySummary(start,end);
    }

    public List<SummaryVo> selectMySummaryVo(String start, String end) {
        long userId = SecurityUtils.getCurrentUserId();
        return workSummaryMapper.selectMySummaryVo(userId,start,end);
    }

    public PageInfo selectUserSummary(Page page, UserSummaryDto userSummaryDto) {
//        uacUserService.se
        PageInfo pageInfo = uacUserService.selectAllOfPage(page, "id");
        List<UacUser> list = pageInfo.getList();
        List<UserSummaryVo> vos = Lists.newArrayList();
        if (!list.isEmpty()){
            UserSummaryVo userSummaryVo = null;
            for (UacUser u:list){
                List<SummaryTreeVo> tree = workSummaryMapper.selectUserSummary(u.getId(),userSummaryDto.getStartTime(),userSummaryDto.getEndTime());
                userSummaryVo = new UserSummaryVo();
                userSummaryVo.setLoginName(u.getLoginName());
                userSummaryVo.setRealName(u.getRealName());
                userSummaryVo.setSummarys(tree);
                userSummaryVo.setUserId(u.getId());
                vos.add(userSummaryVo);
            }
        }
        return new PageInfo(vos);
    }
}
