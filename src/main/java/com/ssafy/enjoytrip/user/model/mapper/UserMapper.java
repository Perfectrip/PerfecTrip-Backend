package com.ssafy.enjoytrip.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.UserDto;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;

	// 이메일 중복 체크 메소드 추가

	void joinUser(UserDto userDto) throws SQLException;

	UserDto loginUser(String userId) throws SQLException;

	UserDto getUser(String userId) throws SQLException;

	void updateUser(UserDto userDto) throws SQLException;

	void deleteUser(String id) throws SQLException;

	String findPassword(String id) throws SQLException;

	// 토큰
	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;

	int updatePassword(Map<String, String> map) throws SQLException;
}
