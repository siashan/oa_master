package com.hanlin.oa.mapper;

import com.hanlin.oa.model.domain.UacUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
* Created by Mybatis Generator on 2019/05/14
*/
public interface UacUserMapper extends Mapper<UacUser>, MySqlMapper<UacUser> {

    public UacUser selectByLoginName(String loginName);
}