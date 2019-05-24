package com.hanlin.oa.service;

import com.hanlin.oa.common.service.BaseService;
import com.hanlin.oa.mapper.ProjectMapper;
import com.hanlin.oa.model.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/20 9:36
 * @Modified By:
 */
@Service
public class ProjectService extends BaseService<Project> {
    @Autowired
    private ProjectMapper projectMapper;

    public void save(Project project) {
        if (null == project.getId()){
            project.setCreateTime(new Date());
            projectMapper.insertSelective(project);
        }else{
            projectMapper.updateByPrimaryKeySelective(project);
        }
    }
}
