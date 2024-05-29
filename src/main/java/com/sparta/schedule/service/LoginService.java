package com.sparta.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.LoginRequestDto;
import com.sparta.schedule.dto.requestdto.UserRequestDto;
import com.sparta.schedule.dto.responsedto.LoginResponseDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.jwt.JwtService;
import com.sparta.schedule.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	public LoginResponseDto login(LoginRequestDto requestDto) {
		User user = userRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

		if (!user.getPassword().equals(requestDto.getPassword())) {
			throw new RuntimeException("회원을 찾을 수 없습니다.");
		}

		String token = jwtService.createToken(user.getUsername(), user.getRole());
		LoginResponseDto responseDto = new LoginResponseDto();
		responseDto.setToken(token);
		responseDto.setMessage("로그인에 성공했습니다.");

		return responseDto;
	}

	public void register(UserRequestDto requestDto) {
		if (userRepository.existsByUsername(requestDto.getUsername())) {
			throw new RuntimeException("중복된 username 입니다.");
		}

		User user = new User(requestDto.getUsername(), requestDto.getPassword(), "ROLE_USER");
		userRepository.save(user);
	}
}