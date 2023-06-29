package com.yahaha.cloud.signup.service.impl;

import com.yahaha.cloud.core.util.EncryptionUtil;
import com.yahaha.cloud.signup.dao.UserMapper;
import com.yahaha.cloud.signup.po.User;
import com.yahaha.cloud.signup.service.ISessionService;
import com.yahaha.cloud.signup.service.IUserService;
import com.yahaha.cloud.signup.vo.SignupUserVO;
import com.yahaha.cloud.signup.vo.ValidAccountVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserServiceImpl implements IUserService {
	private final UserMapper userMapper;

	private final ISessionService sessionService;

	public UserServiceImpl(UserMapper userMapper, ISessionService sessionService) {
		this.userMapper = userMapper;
		this.sessionService = sessionService;
	}

	@Override
	public String createUserAndResponseToSignIn(SignupUserVO vo) throws Exception {
		User user = new User();
		user.setName(vo.getNickname());
		String privateKey = sessionService.getPrivateKeyBySessionId(vo.getSessionId());
		String account = EncryptionUtil.RSA.decrypt(vo.getAccount(), privateKey);
		String password = EncryptionUtil.RSA.decrypt(vo.getPassword(), privateKey);
		user.setAccount(account);
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		user.setPassword(bpe.encode(password));
		userMapper.createUser(user);
		return "注册成功!跳转登录中...";
	}

	@Override
	public ValidAccountVO validAccount(String account) {
		User user = userMapper.selectOneByAccount(account);
		if (user == null) {
			return new ValidAccountVO(ValidAccountVO.LEGAL_STATUS, "用户名合法");
		}
		return new ValidAccountVO(ValidAccountVO.ILLEGAL_STATUS, "用户名已经存在");
	}
}
