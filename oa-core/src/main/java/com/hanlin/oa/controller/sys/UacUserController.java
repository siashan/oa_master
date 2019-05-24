package com.hanlin.oa.controller.sys;

import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.VO.UserEditVo;
import com.hanlin.oa.model.domain.UacUser;
import com.hanlin.oa.service.UacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/17 14:06
 * @Modified By:
 */
@RestController
@RequestMapping("user")
public class UacUserController extends BaseController {
    @Autowired
    private UacUserService uacUserService;

    @GetMapping("")
    public Wrapper list(){
        PageInfo pageInfo = uacUserService.selectAllOfPage(initPage(), "id desc");
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("save")
    public Wrapper save(UacUser uacUser,long[] roles){
        uacUserService.save(uacUser,roles);
        return WrapMapper.ok();
    }

    @GetMapping("getUser")
    public Wrapper getUserEditVo(Long id){
        UserEditVo editVo = uacUserService.selectUserEditVo(id);
        return WrapMapper.ok(editVo);
    }

    @PostMapping("resetPwd")
    public Wrapper resetPwd(Long id){
       uacUserService.resetPwd(id);
        return WrapMapper.ok();
    }
}
