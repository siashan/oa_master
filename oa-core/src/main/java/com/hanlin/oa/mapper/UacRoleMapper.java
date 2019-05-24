package com.hanlin.oa.mapper;

import com.hanlin.oa.model.VO.RoleBtnVo;
import com.hanlin.oa.model.domain.UacRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
* Created by Mybatis Generator on 2019/05/14
*/
public interface UacRoleMapper extends Mapper<UacRole>, MySqlMapper<UacRole> {
    List<RoleBtnVo> selectRoleBtnVoList();
}