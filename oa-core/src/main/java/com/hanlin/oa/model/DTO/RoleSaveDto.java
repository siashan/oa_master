package com.hanlin.oa.model.DTO;

import lombok.Data;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/17 17:20
 * @Modified By:
 */
@Data
public class RoleSaveDto {
    private long id;
    private String roleName;
    private String remark;
    private String menus;
}
