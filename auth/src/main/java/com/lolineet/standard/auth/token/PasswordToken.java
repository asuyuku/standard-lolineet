/**
 * @Author zhanghongwei
 * @Date 2023 03 13 11 20
 **/
package com.lolineet.standard.auth.token;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
public class PasswordToken extends AbstractAuthenticationToken {
    private Object principal;

    private String password;

    public PasswordToken(Object principal, String password) {
        super(null);
        this.principal = principal;
        this.password = password;
        this.setAuthenticated(false);
    }

    public PasswordToken(Object principal, String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.password = password;
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.password = null;
    }
}
