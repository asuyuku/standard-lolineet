/**
 * @Author zhanghongwei
 * @Date 2023 03 13 11 38
 **/
package com.lolineet.standard.auth.controller;

import com.lolineet.standard.R;
import com.lolineet.standard.auth.handler.SuccessHandler;
import com.lolineet.standard.auth.token.PasswordToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "认证接口")
@RestController
@RequestMapping("/oauth")
public class OAuthController   {

    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    public R login(HttpServletRequest request,HttpServletResponse response,String username, String password) throws ServletException, IOException {

        Authentication authentication = new PasswordToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        new SuccessHandler().onAuthenticationSuccess(request, response, authentication);

        return R.ok();
    }

}
