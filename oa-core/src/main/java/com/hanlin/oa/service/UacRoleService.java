package com.hanlin.oa.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.common.collect.Lists;
import com.hanlin.oa.common.service.BaseService;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.mapper.UacRoleMapper;
import com.hanlin.oa.mapper.UacRoleMenuMapper;
import com.hanlin.oa.mapper.UacUserRoleMapper;
import com.hanlin.oa.model.VO.RoleBtnVo;
import com.hanlin.oa.model.VO.RoleEditVo;
import com.hanlin.oa.model.domain.UacRole;
import com.hanlin.oa.model.domain.UacRoleMenu;
import com.hanlin.oa.model.domain.UacUserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/17 14:41
 * @Modified By:
 */
@Service
public class UacRoleService extends BaseService<UacRole> {
    @Autowired
    private UacRoleMapper uacRoleMapper;
    @Autowired
    private UacRoleMenuMapper uacRoleMenuMapper;
    @Autowired
    private UacUserRoleMapper uacUserRoleMapper;


    public List<RoleBtnVo> selectRoleBtnVoList() {
        return uacRoleMapper.selectRoleBtnVoList();
    }

    public void save(UacRole uacRole, long[] menus) {
        long currentUserId = SecurityUtils.getCurrentUserId();
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        Date date = new Date();
        uacRole.setLastOperatorId(currentUserId);
        uacRole.setLastOperarot(currentLoginName);
        uacRole.setLastUpdateTime(date);
        if (null == uacRole.getId()) {
            uacRole.setCreateTime(date);
            uacRole.setCreatorId(currentUserId);
            uacRole.setCreator(currentLoginName);
            uacRoleMapper.insertSelective(uacRole);
        } else {
            uacRoleMapper.updateByPrimaryKeySelective(uacRole);
            uacRoleMenuMapper.deleteByRoleId(uacRole.getId());
        }

        ArrayList<UacRoleMenu> roleMenuLists = Lists.newArrayList();
        UacRoleMenu uacRoleMenu ;
        for (long menuid :menus){
            uacRoleMenu = new UacRoleMenu();
            uacRoleMenu.setRoleId(uacRole.getId());
            uacRoleMenu.setMenuId(menuid);
            roleMenuLists.add(uacRoleMenu);
        }
        uacRoleMenuMapper.insertList(roleMenuLists);
    }


    @Transactional(rollbackFor = Exception.class)
    public int  delete(long roleId){
        List<UacUserRole> uacUserRoles = uacUserRoleMapper.selectByRoleId(roleId);
        if (!uacUserRoles.isEmpty()){
            uacUserRoleMapper.deleteByRoleId(roleId);
        }
        uacRoleMenuMapper.deleteByRoleId(roleId);
        return uacRoleMapper.deleteByPrimaryKey(roleId);
    }

    public RoleEditVo selectRoleEditVo(Long id) {
        UacRole uacRole = selectByPrimaryKey(id);
        List menus = uacRoleMenuMapper.selectMenuIdsByRoleId(id);
        RoleEditVo editVo = new ModelMapper().map(uacRole,RoleEditVo.class);
        editVo.setMenus(menus);
        return editVo;
    }
}
