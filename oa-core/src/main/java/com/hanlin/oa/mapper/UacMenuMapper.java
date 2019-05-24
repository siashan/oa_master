package com.hanlin.oa.mapper;

import com.hanlin.oa.model.VO.MenuTreeVo;
import com.hanlin.oa.model.VO.MenuVo;
import com.hanlin.oa.model.domain.UacMenu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Mybatis Generator on 2019/05/14
*/
public interface UacMenuMapper extends Mapper<UacMenu>, MySqlMapper<UacMenu> {
    List<UacMenu> getUserFatherMenuList(Long id);

    List<MenuVo> findMenuVoListByUserId(Long userId);

    ArrayList<MenuTreeVo> selectMenuTreeVoAll();

    List<UacMenu> selectOwnGrantedAuthoritiesByUserId(Long userId);
}