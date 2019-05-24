package com.hanlin.oa.common.support.security;

import com.hanlin.oa.model.domain.UacUser;
import com.hanlin.oa.service.UacUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/15 11:16
 * @Modified By:
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UacUserService uacUserService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("请求到了");
        UacUser uacUser = uacUserService.findByLoginName(s);
        if (null == uacUser) {
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }

        // 加载用户权限
        Collection<GrantedAuthority> grantedAuthorities = uacUserService.selectOwnGrantedAuthoritiesByUserId(uacUser.getId());
        return new SecurityUser((long) uacUser.getId(), uacUser.getLoginName(), uacUser.getLoginPassword()
                , uacUser.getStatus(), grantedAuthorities);
    }
}
