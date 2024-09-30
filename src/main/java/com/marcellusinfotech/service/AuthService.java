package com.marcellusinfotech.service;

import com.marcellusinfotech.entity.dto.LoginDto;
import com.marcellusinfotech.entity.dto.RegisterDto;

public interface AuthService {
	
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
