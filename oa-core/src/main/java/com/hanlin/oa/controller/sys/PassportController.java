package com.hanlin.oa.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.security.SecurityUtils;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.domain.UacUser;
import com.hanlin.oa.service.UacMenuService;
import com.hanlin.oa.service.UacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/16 11:08
 * @Modified By:
 */
@RestController
@RequestMapping("")
public class PassportController extends BaseController {

    @Autowired
    private UacMenuService uacMenuService;
    @Autowired
    private UacUserService uacUserService;

    @GetMapping("userInfo")
    public Wrapper userInfo(){
        Map<String,Object> map = new HashMap<>();
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        UacUser uacuser = uacUserService.findByLoginName(currentLoginName);
        ArrayList<JSONObject> userMenu = uacMenuService.getUserMenu(uacuser.getId());
        map.put("roles",userMenu);
        return WrapMapper.ok(map);
    }
}
