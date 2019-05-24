package com.hanlin.oa.mapper;

import com.hanlin.oa.model.domain.UacRoleMenu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
* Created by Mybatis Generator on 2019/05/14
*/
public interface UacRoleMenuMapper extends Mapper<UacRoleMenu>, MySqlMapper<UacRoleMenu> {
    void deleteByRoleId(Long roleId);

    List selectMenuIdsByRoleId(Long roleId);
}