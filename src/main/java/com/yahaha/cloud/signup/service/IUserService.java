package com.yahaha.cloud.signup.service;

import com.yahaha.cloud.core.object.CommonResponse;
import com.yahaha.cloud.signup.vo.SignupUserVO;
import com.yahaha.cloud.signup.vo.ValidAccountVO;

public interface IUserService {
	String createUserAndResponseToSignIn(SignupUserVO vo) throws Exception;

	ValidAccountVO validAccount(String account);
}
