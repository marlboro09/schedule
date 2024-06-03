package com.sparta.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.UserRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.repository.UserRepository;

@Service
public class UserService {

	@Value("${admin.token}")
	private String adminToken;

	@Autowired
	private UserRepository userRepository;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void register(UserRequestDto requestDto) {
		if (userRepository.existsByUsername(requestDto.getNickname())) {
			throw new RuntimeException("중복된 nickname 입니다.");
		}

		UserRoleEnum role = UserRoleEnum.USER;
		if (requestDto.isAdmin()) {
			if (!adminToken.equals(requestDto.getAdminToken())) {
				throw new RuntimeException("잘못된 관리자 토큰입니다.");
			}
			role = UserRoleEnum.ADMIN;
		}

		String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
		User user = new User(requestDto.getUsername(), encodedPassword, role);
		user.setNickname(requestDto.getNickname());
		userRepository.save(user);
	}
}