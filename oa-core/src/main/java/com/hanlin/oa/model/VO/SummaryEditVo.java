package com.hanlin.oa.model.VO;

import lombok.Data;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/23 14:59
 * @Modified By:
 */
@Data
public class SummaryEditVo {
    private long id;
    private long projectId;
    private long divisionId;
    private String content;
    private  String workTime;
}
