package com.ssafy.enjoytrip.user.model.service;

import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;

	int joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(String userId, String userPwd) throws Exception;

	void updateUser(UserDto userDto) throws Exception;

	void deleteUser(String id) throws Exception;

	String findPassword(String id) throws Exception;
}
