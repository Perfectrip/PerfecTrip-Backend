package com.ssafy.enjoytrip.user.model.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.idCheck(id);
	}

	@Override
	public int joinUser(UserDto userDto) throws Exception {
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		return userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(String userId, String userPwd) throws Exception {
		UserDto userDto = userMapper.loginUser(userId);
		if (userDto != null && BCrypt.checkpw(userPwd, userDto.getPassword())) {
			return userDto;
		}
		return null;
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		userMapper.updateUser(userDto);

	}

	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);

	}

	@Override
	public String findPassword(String id) throws Exception {
		return userMapper.findPassword(id);
	}

}
