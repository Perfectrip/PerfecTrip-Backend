package com.ssafy.enjoytrip.user.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.MailDto;
import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	public void joinUser(UserDto userDto) throws Exception {
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(String userId, String userPwd) throws Exception {
		UserDto userDto = userMapper.loginUser(userId);
		if (userDto != null && BCrypt.checkpw(userPwd, userMapper.getUser(userId).getPassword())) {
			return userDto; // id, name만 있는 userDto
		}

		return null;
	}

	@Override
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId); // 모든 필드 정보를 담고 있는 UserDto
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

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userMapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

	@Override
	public MailDto createMailAndChangePassword(String userEmail) throws Exception {
		String str = getTempPassword();
		MailDto mailDto = new MailDto();
		mailDto.setAddress(userEmail);
		mailDto.setTitle("PerfecTrip 임시비밀번호 안내 이메일 입니다.");
		mailDto.setMessage("안녕하세요. PerfecTrip 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 " + str + " 입니다."
				+ "로그인 후에 비밀번호를 변경을 해주세요");

		// BCrypt 적용 후 DB에 저장
		if (updatePassword(BCrypt.hashpw(str, BCrypt.gensalt()), userEmail) == 1)
			return mailDto;
		else
			return null;
	}

	@Override
	public int updatePassword(String str, String userEmail) throws Exception {
		String newPassword = str;
		Map<String, String> map = new HashMap<>();
		map.put("userEmail", userEmail);
		map.put("newPassword", newPassword);
		return userMapper.updatePassword(map);
	}

	@Override
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String str = "";

		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}

	@Override
	public void mailSend(MailDto mailDto) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDto.getAddress());
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		message.setFrom("phsk710@naver.com");
		message.setReplyTo("phsk710@naver.com");
		mailSender.send(message);
	}

}
