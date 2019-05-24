package com.hanlin.oa.common.support.security;

import com.google.common.collect.Sets;
import com.hanlin.oa.model.domain.UacUser;
import jodd.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;


/**
 * The class Security utils.
 *
 * @author paascloud.net @gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

    private static final String AUTH_LOGIN_AFTER_URL = "/user/loginAfter/*";
    private static final String AUTH_LOGOUT_URL = "/user/logout";

    /**
     * Gets current login name.
     *
     * @return the current login name
     */
    public static String getCurrentLoginName() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {

            return ((UserDetails) principal).getUsername();

        }

        if (principal instanceof Principal) {

            return ((Principal) principal).getName();

        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return String.valueOf(principal);

    }


    public static long getCurrentUserId() {
        return  getCurrentUser().getUserId();
    }

    public static SecurityUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((SecurityUser) principal);
    }

    public static Set<String> getCurrentAuthorityUrl() {
        Set<String> path = Sets.newHashSet();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            String url = authority.getAuthority();
            if (StringUtil.isNotEmpty(url)) {
                path.add(url);
            }
        }
        path.add(AUTH_LOGIN_AFTER_URL);
        path.add(AUTH_LOGOUT_URL);
        return path;
    }
}
