package com.hanlin.oa.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/19 15:03
 * @Modified By:
 */
@Data
public class RoleEditVo {
    private long id;
    private String roleName;
    private String remark;
    private List<Long> menus;
}
