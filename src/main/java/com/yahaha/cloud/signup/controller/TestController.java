package com.yahaha.cloud.signup.controller;

import com.yahaha.cloud.signup.dao.UserMapper;
import com.yahaha.cloud.signup.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	private final UserMapper userMapper;

	public TestController(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/createUser")
	public void creatUser() {
		User u = new User();
		logger.info(u.getId());
	}

	@RequestMapping(method = RequestMethod.POST, value = "insertUser")
	public void insertUser() {
		User u = new User();
		u.setName("hello");
		userMapper.createUser(u);
	}
}
