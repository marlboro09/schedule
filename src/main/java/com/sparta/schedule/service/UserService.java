package com.sparta.schedule.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.LoginRequestDto;
import com.sparta.schedule.dto.requestdto.UserRequestDto;
import com.sparta.schedule.dto.responsedto.LoginResponseDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void register(UserRequestDto requestDto) {
		userRepository.findByUsername(requestDto.getUsername())
			.ifPresent(user -> {
				throw new RuntimeException("중복된 username 입니다.");
			});

		User user = new User();
		user.setNickname(requestDto.getNickname());
		user.setUsername(requestDto.getUsername());
		user.setPassword(requestDto.getPassword());
		user.setRole("USER");
		user.setCreatedDate(LocalDateTime.now());

		userRepository.save(user);
	}

	public LoginResponseDto login(LoginRequestDto requestDto) {
		return null;
	}
}
