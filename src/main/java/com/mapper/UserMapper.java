package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.entity.User;

@Mapper
public interface UserMapper {
	
	public User selectByEmail(String email);
	
	public void insert(User user);
	
	public List<User> selectAll();

}