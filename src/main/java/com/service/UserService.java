package com.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dto.UserDto;
import com.entity.User;
import com.enums.Role;
import com.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserMapper userMapper;

	private PasswordEncoder encoder;
	
	@Transactional
	public void signup(UserDto userDto) {
		User user = this.userMapper.selectByEmail(userDto.getEmail());
		if (Objects.nonNull(user)) {
			throw new DuplicateKeyException("data is unique key");
		}
		User insertedUser = new User();
		BeanUtils.copyProperties(userDto, insertedUser);
		insertedUser.setPassword(encoder.encode(userDto.getPassword()));
		insertedUser.setRoleCode(Role.GENERAL.getCode());
		this.userMapper.insert(insertedUser);
	}
	
	public List<UserDto> getUserList() {
		List<User> users = userMapper.selectAll();
		return users.stream().map(user -> {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(user, dto);
			dto.setRoleValue(Role.getValueByCode(user.getRoleCode()));
			return dto;
		}).collect(Collectors.toList());
	}
}