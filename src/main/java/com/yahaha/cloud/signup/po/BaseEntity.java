package com.yahaha.cloud.signup.po;

import com.yahaha.cloud.core.object.SnowFlake;
import com.yahaha.cloud.core.util.SnowFlakeUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = -3357818765307938546L;

	private String id;

	private String name;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private String createId;

	private String updateId;

	public BaseEntity() {
		this.id = SnowFlakeUtil.nextId();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public String getCreateId() {
		return createId;
	}

	public String getUpdateId() {
		return updateId;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BaseEntity ? ((BaseEntity) obj).getId().equals(this.id) : false;
	}
}
