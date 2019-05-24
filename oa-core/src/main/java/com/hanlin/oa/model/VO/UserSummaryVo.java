package com.hanlin.oa.model.VO;

import lombok.Data;

import java.util.List;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/23 10:26
 * @Modified By:
 */
@Data
public class UserSummaryVo {
    private long userId;
    private String loginName;
    private String realName;
    private List<SummaryTreeVo> summarys;
}
