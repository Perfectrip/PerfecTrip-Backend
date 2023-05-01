package com.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;

	int joinUser(UserDto userDto) throws SQLException;

	UserDto loginUser(String userId) throws SQLException;

	void updateUser(UserDto userDto) throws SQLException;

	void deleteUser(String id) throws SQLException;

	String findPassword(String id) throws SQLException;
}
