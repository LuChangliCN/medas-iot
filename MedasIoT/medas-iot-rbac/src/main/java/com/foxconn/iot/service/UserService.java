package com.foxconn.iot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foxconn.iot.dto.UserDto;
import com.foxconn.iot.dto.UserDetailDto;

public interface UserService {
	
	void create(UserDto user);
	
	void save(UserDto user);
	
	UserDetailDto findById(long id);
	
	UserDto findByNO(String no);
	
	void updateStatusById(int status, long id);
	
	void deleteById(long id);
	
	Page<UserDetailDto> queryByCompany(long companyid, Pageable pageable);
	
	void updatePwdById(String pwd, long id);
}