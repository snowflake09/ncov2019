package com.service.biz.impl;

import com.service.biz.TokenService;
import com.utils.em.UserType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class TokenServiceImpl implements TokenService{

	
	@Override
	public boolean checkToken(String token, int userType) {
		return false;
	}

}
