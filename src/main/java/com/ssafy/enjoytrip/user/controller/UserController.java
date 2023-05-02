package com.ssafy.enjoytrip.user.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.user.model.UserDto;
import com.ssafy.enjoytrip.user.model.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/join")
	public ResponseEntity<?> userJoin(@RequestBody UserDto userDto) {

		logger.debug("유저 등록 UserDto : {}", userDto);
		
		try {
			userService.joinUser(userDto);
			return new ResponseEntity<String>("회원가입 완료!!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("회원가입 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> param){
		try {
			UserDto result = userService.loginUser(param.get("id"), param.get("password"));
			
			return new ResponseEntity<UserDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("로그인 실패!!!", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
