package com.hanlin.oa.interceptor;

import com.hanlin.oa.common.support.anno.ReCommitAnno;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/19 18:02
 * @Modified By:
 */
public class ReCommitInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handleMethod = (HandlerMethod) handler;
        ReCommitAnno reCommitAnno = handleMethod.getMethodAnnotation(ReCommitAnno.class);
        ResponseBody ajax = handleMethod.getMethodAnnotation(ResponseBody.class);
        if (null == reCommitAnno) {
            // 没有声明权限,放行
            return true;
        }
        return true;
    }
}
