package com.yahaha.cloud.signup.vo;

import java.io.Serial;
import java.io.Serializable;

public class ValidAccountVO implements Serializable {
	@Serial
	private static final long serialVersionUID = -1824849201834473111L;

	private Integer status;

	private String tips;

	public static final Integer ILLEGAL_STATUS = 1;

	public static final Integer LEGAL_STATUS = 0;

	public ValidAccountVO(Integer status, String tips) {
		this.status = status;
		this.tips = tips;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTips() {
		return tips;
	}
}
