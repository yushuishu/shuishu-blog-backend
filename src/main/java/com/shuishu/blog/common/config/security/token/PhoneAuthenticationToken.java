package com.shuishu.blog.common.config.security.token;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Collection;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-05 14:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：手机号 token
 * <p></p>
 */
public class PhoneAuthenticationToken extends UsernamePasswordAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 703964561607984110L;

    public PhoneAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PhoneAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(principal, "", authorities);
    }

}
