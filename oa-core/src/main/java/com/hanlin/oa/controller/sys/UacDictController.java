package com.hanlin.oa.controller.sys;

import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.domain.UacDict;
import com.hanlin.oa.service.UacDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/14 19:34
 * @Modified By:
 */
@RestController
@RequestMapping("dict")
public class UacDictController extends BaseController{

    @Autowired
    private UacDictService uacDictService;


    @GetMapping("")
    public Wrapper list(){
        PageInfo pageInfo = uacDictService.selectAllOfPage(initPage(), "dict_group ,id ");
        return WrapMapper.ok(pageInfo);
    }
    @PostMapping("save")
    public Wrapper save(UacDict dict ){
        if (null == dict.getId()){
            uacDictService.insertSelective(dict);
        }else{
            uacDictService.updateByPrimaryKeySelective(dict);
        }
        return  WrapMapper.ok();
    }

    @PostMapping("del/{id}")
    public Wrapper delDict(@PathVariable Long id){
        return  uacDictService.deleteByPrimaryKey(id) > 0 ? WrapMapper.ok() : WrapMapper.error();
    }
}
