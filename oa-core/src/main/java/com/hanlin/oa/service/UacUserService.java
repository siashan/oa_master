package com.hanlin.oa.service;

import com.google.common.collect.Lists;
import com.hanlin.oa.common.service.BaseService;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.mapper.UacMenuMapper;
import com.hanlin.oa.mapper.UacUserMapper;
import com.hanlin.oa.mapper.UacUserRoleMapper;
import com.hanlin.oa.model.VO.UserEditVo;
import com.hanlin.oa.model.domain.UacMenu;
import com.hanlin.oa.model.domain.UacUser;
import com.hanlin.oa.model.domain.UacUserRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/15 11:36
 * @Modified By:
 */
@Service
public class UacUserService extends BaseService<UacUser> {

    @Autowired
    private UacUserMapper uacUserMapper;

    @Autowired
    private UacMenuMapper uacMenuMapper;

    @Autowired
    private UacUserRoleMapper uacUserRoleMapper;

   public UacUser findByLoginName(String loginName){
        return  uacUserMapper.selectByLoginName(loginName);
    }

    public Collection<GrantedAuthority> selectOwnGrantedAuthoritiesByUserId(Long userId) {
       List<UacMenu> menuList = uacMenuMapper.selectOwnGrantedAuthoritiesByUserId(userId);
        List<GrantedAuthority> authList = Lists.newArrayList();
        for (UacMenu action : menuList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(action.getUrl());
            authList.add(grantedAuthority);
        }
        return authList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(UacUser uacUser, long[] roles) {
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        long currentUserId = SecurityUtils.getCurrentUserId();
        Date date = new Date();
        uacUser.setLastOperator(currentLoginName);
       uacUser.setLastOperatorId(currentUserId);
        if (null == uacUser.getId()){
            uacUser.setCreateTime(date);
            setDefaultPwd(uacUser);
            uacUser.setCreatorId(currentUserId);
            uacUser.setCreator(currentLoginName);
            uacUserMapper.insertSelective(uacUser);
        }else{
            uacUserMapper.updateByPrimaryKeySelective(uacUser);
        }
        uacUserRoleMapper.deleteByUserId(uacUser.getId());
        ArrayList<UacUserRole> uacUserRoles = Lists.newArrayList();
        UacUserRole uacUserRole = null;
        for (long roleId:roles){
            uacUserRole = new UacUserRole();
            uacUserRole.setRoleId(roleId);
            uacUserRole.setUserId(uacUser.getId());
            uacUserRoles.add(uacUserRole);
        }
        uacUserRoleMapper.insertList(uacUserRoles);
    }

    public UserEditVo selectUserEditVo(Long id) {
        UacUser uacUser = selectByPrimaryKey(id);

        List<Long> roles = uacUserRoleMapper.selectRolesByUserId(id);

        UserEditVo editVo = new ModelMapper().map(uacUser,UserEditVo.class);
        editVo.setRoles(roles);
        return editVo;
    }


    private UacUser setDefaultPwd(UacUser uacUser){
        String defaultPwd = "hlguy_2019";
        uacUser.setLoginPassword(new BCryptPasswordEncoder().encode(defaultPwd));
        return uacUser;
    }


    public void resetPwd(Long id){
        UacUser uacUser = uacUserMapper.selectByPrimaryKey(id);
        setDefaultPwd(uacUser);
        uacUserMapper.updateByPrimaryKeySelective(uacUser);
    }

}
