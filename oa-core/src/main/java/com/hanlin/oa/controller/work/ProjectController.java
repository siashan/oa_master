package com.hanlin.oa.controller.work;

import com.github.pagehelper.PageInfo;
import com.hanlin.oa.common.controller.BaseController;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import com.hanlin.oa.common.support.wrapper.Wrapper;
import com.hanlin.oa.model.domain.Project;
import com.hanlin.oa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/20 9:35
 * @Modified By:
 */
@RestController
@RequestMapping("project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public Wrapper list(){
        PageInfo pageInfo = projectService.selectAllOfPage(initPage(), "id desc");
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("save")
    public Wrapper save(Project project){
        projectService.save(project);
        return WrapMapper.ok();
    }

    @PostMapping("del")
    public Wrapper del(Long id){
        return projectService.deleteByPrimaryKey(id) > 0 ? WrapMapper.ok(): WrapMapper.error();
    }

}
