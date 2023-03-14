/**
 * @Author zhanghongwei
 * @Date 2023 03 13 11 25
 **/
package com.lolineet.standard.auth.provider;

import com.lolineet.standard.auth.token.PasswordToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class PasswordProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordToken token = (PasswordToken) authentication;
        String username = "testName";
        token = new PasswordToken(username, null, null);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
