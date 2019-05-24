package com.hanlin.oa.controller.sys;

import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.mapper.UacRoleMenuMapper;
import com.hanlin.oa.model.VO.RoleBtnVo;
import com.hanlin.oa.model.VO.RoleEditVo;
import com.hanlin.oa.model.domain.UacRole;
import com.hanlin.oa.model.domain.UacRoleMenu;
import com.hanlin.oa.service.UacRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/17 14:40
 * @Modified By:
 */
@RestController
@RequestMapping("role")
public class UacRoleController extends BaseController {

    @Autowired
    private UacRoleService uacRoleService;
    @Autowired
    private UacRoleMenuMapper uacRoleMenuMapper;

    @GetMapping("")
    public Wrapper list(){
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        System.out.println(currentLoginName);
        PageInfo pageInfo = uacRoleService.selectAllOfPage(initPage(), "id");
        return WrapMapper.ok(pageInfo);
    }

    @GetMapping("loadRoles")
    public Wrapper loadRoles(){
        List<RoleBtnVo> voList =  uacRoleService.selectRoleBtnVoList();
        return WrapMapper.ok(voList);
    }

    @PostMapping("save")
    public Wrapper save(UacRole uacRole,long[] menus){
        uacRoleService.save(uacRole,menus);
        return WrapMapper.ok();
    }

    @PostMapping("del")
    public Wrapper del(Long id){
       return uacRoleService.delete(id) > 0 ? WrapMapper.ok() :WrapMapper.error();
    }

    @GetMapping("getRole")
    public Wrapper getRole(Long id){
        RoleEditVo editVo = uacRoleService.selectRoleEditVo(id);
        return WrapMapper.ok(editVo);
    }
}
