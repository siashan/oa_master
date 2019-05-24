package com.hanlin.oa.common.support.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanlin.oa.common.support.wrapper.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限拒绝处理器
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/16 10:26
 * @Modified By:
 */
@Slf4j
@Component
public class PcAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.info("处理权限异常. e={}", e);
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 99990401);
        result.put("message", "无访问权限");
        String json = objectMapper.writeValueAsString(result);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}
