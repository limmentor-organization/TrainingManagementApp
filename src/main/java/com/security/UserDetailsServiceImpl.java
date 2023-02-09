package com.security;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.enums.Role;
import com.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Component("UserDetailsServiceImpl")
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User loginUser = userMapper.selectByEmail(email);
			UserDetailsImpl userDetailImpl = new UserDetailsImpl();
			BeanUtils.copyProperties(loginUser, userDetailImpl);
			userDetailImpl.setAuthority(AuthorityUtils.createAuthorityList(Role.getValueByCode(loginUser.getRoleCode())));
			return userDetailImpl;
		} catch (Exception e) {
			throw new UsernameNotFoundException("user not found.", e);
		}
	}
}
