package com.yahaha.cloud.signup.controller;

import com.yahaha.cloud.core.object.CommonResponse;
import com.yahaha.cloud.core.util.CommonResponseBuilder;
import com.yahaha.cloud.signup.service.IRSAService;
import com.yahaha.cloud.signup.service.ISessionService;
import com.yahaha.cloud.signup.service.IUserService;
import com.yahaha.cloud.signup.vo.SignupUserVO;
import com.yahaha.cloud.signup.vo.ValidAccountVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class MainController {
	private final IUserService userService;

	private final ISessionService sessionService;

	public MainController(IUserService userService, ISessionService sessionService) {
		this.userService = userService;
		this.sessionService = sessionService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public CommonResponse<String> signupUser(@RequestBody SignupUserVO vo) throws Exception {
		return CommonResponseBuilder.successResponse(userService.createUserAndResponseToSignIn(vo));
	}

	@RequestMapping(value = "/user/publicKey", method = RequestMethod.GET)
	public CommonResponse<String> getUserPublicKeyBySessionId(String sessionId) throws Exception {
		return CommonResponseBuilder.successResponse(sessionService.getPublicKeyBySessionId(sessionId));
	}

	@RequestMapping(value = "/user/sessionId", method = RequestMethod.GET)
	public CommonResponse<String> createSessionId() throws Exception {
		return CommonResponseBuilder.successResponse(sessionService.createSessionId());
	}

	@RequestMapping(value = "/user/account/valid/result", method = RequestMethod.GET)
	public CommonResponse<ValidAccountVO> validAccount(String account) {
		return CommonResponseBuilder.successResponse(userService.validAccount(account));
	}
}
