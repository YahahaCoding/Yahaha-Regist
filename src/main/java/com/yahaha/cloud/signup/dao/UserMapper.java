package com.yahaha.cloud.signup.dao;

import com.yahaha.cloud.signup.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	int createUser(User user);

	User selectOneByAccount(@Param("account") String account);
}
