package com.hanlin.oa.mapper;

import com.hanlin.oa.model.domain.UacUserRole;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
* Created by Mybatis Generator on 2019/05/14
*/
public interface UacUserRoleMapper extends Mapper<UacUserRole>, MySqlMapper<UacUserRole> {
   List<UacUserRole> selectByRoleId(long roleId);

    void deleteByRoleId(long roleId);

    void deleteByUserId(Long userId);

    List<Long> selectRolesByUserId(Long userId);
}