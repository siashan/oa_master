package com.hanlin.oa.controller.sys;

import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.VO.MenuEditVo;
import com.hanlin.oa.model.domain.UacMenu;
import com.hanlin.oa.service.UacMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2018/7/6 11:21
 * @Modified By:
 */
@RestController
@RequestMapping("menu")
public class UacMenuController extends BaseController {
    @Autowired
    private UacMenuService uacMenuService;

    @GetMapping("")
    public Wrapper menuList(){
        return WrapMapper.ok(uacMenuService.selectMenuVoTree());
    }

    @PostMapping("del")
    public Wrapper del(Long id){
        return uacMenuService.deleteByPrimaryKey(id) > 0 ? WrapMapper.ok():WrapMapper.error();
    }

    @PostMapping("save")
    public Wrapper save(UacMenu menu){
        uacMenuService.save(menu);
        return WrapMapper.ok();
    }

    @GetMapping("getMenu")
    public Wrapper getMenu(Long id){
        MenuEditVo editVo = uacMenuService.selectMenuEditVo(id);
        return WrapMapper.ok(editVo);
    }
}
