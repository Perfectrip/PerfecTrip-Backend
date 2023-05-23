package com.ssafy.enjoytrip.user.model.service;

import com.ssafy.enjoytrip.user.model.MailDto;
import com.ssafy.enjoytrip.user.model.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;

	void joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(String userId, String userPwd) throws Exception;

	UserDto getUser(String userId) throws Exception;

	void updateUser(UserDto userDto) throws Exception;

	void deleteUser(String id) throws Exception;

	String findPassword(String id) throws Exception;

	void saveRefreshToken(String userid, String refreshToken) throws Exception;

	Object getRefreshToken(String userid) throws Exception;

	void deleRefreshToken(String userid) throws Exception;

	MailDto createMailAndChangePassword(String userEmail) throws Exception;

	int updatePassword(String str, String userEmail) throws Exception;

	String getTempPassword();

	void mailSend(MailDto mailDto);
}
