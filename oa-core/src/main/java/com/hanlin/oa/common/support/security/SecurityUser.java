/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：SecurityUser.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.hanlin.oa.common.support.security;

import jodd.util.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The class Security user.
 *
 * @author paascloud.net @gmail.com
 */
public class SecurityUser implements UserDetails {
	private static final long serialVersionUID = 4872628781561412463L;

	// 用户是否可用
	private static final String ENABLE = "1";

	private Collection<GrantedAuthority> authorities;

	private Long userId;

	private String loginName;

	private String loginPwd;

	private String status;

	public SecurityUser(Long userId, String loginName, String loginPwd) {
		this.setUserId(userId);
		this.setLoginName(loginName);
		this.setLoginPwd(loginPwd);
	}

	public SecurityUser(Long userId, String loginName, String loginPwd, String status, Collection<GrantedAuthority> grantedAuthorities) {
		this.setUserId(userId);
		this.setLoginName(loginName);
		this.setLoginPwd(loginPwd);
		this.setStatus(status);
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.getLoginPwd();
	}

	@Override
	public String getUsername() {
		return this.getLoginName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return StringUtil.equals(this.status ,ENABLE);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
