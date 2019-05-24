package com.hanlin.oa.common.controller;

import com.hanlin.oa.common.support.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/14 19:34
 * @Modified By:
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest req;
    /**
     * Description: 获取分页对象 <br/>
     *
     * @param:
     * @return:
     * @Author: kfc
     * @Date: 2018/4/2 9:46
     */
    protected Page initPage() {
        String cur = req.getParameter("currentPage");
        String size = req.getParameter("pageSize");
        int curPage = (cur == null || "0".equals(cur) ? 1 : Integer.parseInt(cur));
        int pageSize = (size == null ? Page.DEFAULT_LENGTH : Integer.parseInt(size));
        return new Page(curPage, pageSize);
    }
}
