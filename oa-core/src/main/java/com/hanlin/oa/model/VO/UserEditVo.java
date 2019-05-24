package com.hanlin.oa.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/19 15:52
 * @Modified By:
 */
@Data
public class UserEditVo {
    private long id;
    private String loginName;
    private String realName;
    private String phoneNo;
    private String email;
    private String gender;
    private String status;
    private List<Long> roles;
}
