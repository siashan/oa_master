package com.hanlin.oa.mapper;

import com.hanlin.oa.model.VO.SummaryTreeVo;
import com.hanlin.oa.model.VO.SummaryVo;
import com.hanlin.oa.model.domain.WorkSummary;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
* Created by Mybatis Generator on 2019/05/20
*/
public interface WorkSummaryMapper extends Mapper<WorkSummary>, MySqlMapper<WorkSummary> {
    List<WorkSummary> selectMySummary(@Param(value = "startTime") String start, @Param(value = "endTime") String end);

    List<SummaryVo> selectMySummaryVo(@Param(value = "userId") long userId,@Param(value = "startTime") String start, @Param(value = "endTime") String end);

    List<SummaryTreeVo> selectUserSummary(@Param(value = "userId") Long userId, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);
}