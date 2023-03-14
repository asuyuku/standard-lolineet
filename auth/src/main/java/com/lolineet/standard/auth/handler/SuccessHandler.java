/**
 * @Author zhanghongwei
 * @Date 2023 03 13 11 30
 **/
package com.lolineet.standard.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.lolineet.standard.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.getWriter().write(JSONObject.toJSONString(R.ok()));
    }
}
