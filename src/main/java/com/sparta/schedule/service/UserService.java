package com.sparta.schedule.service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.LoginRequestDto;
import com.sparta.schedule.dto.LoginResponseDto;
import com.sparta.schedule.dto.SignupRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.exception.NotFoundException;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	//테스트용 토큰
	private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

	/**
	 * 회원가입
	 */
	public void signup(SignupRequestDto requestDto) {
		String nickname = requestDto.getNickname();
		String username = requestDto.getUsername();
		String password = requestDto.getPassword();

		//회원 중복 확인
		Optional<User> checkUsername = userRepository.findByUsername(username);
		if (checkUsername.isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
		}

		//사용자 ROLE 확인
		UserRoleEnum role = UserRoleEnum.USER;
		if (requestDto.isAdmin()) {
			if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())){
				throw new IllegalArgumentException("관리자 암호가 틀렸습니다.");
			}
			role = UserRoleEnum.ADMIN;
		}
		LocalDateTime time = LocalDateTime.now();

		//사용자 등록
		User user = new User(null, requestDto.getNickname(), requestDto.getUsername(), requestDto.getPassword(), role, time);
		userRepository.save(user);
	}

	public LoginResponseDto login(LoginRequestDto requestDto) {
		User user = userRepository.findByUsername(requestDto.getUsername())
			.orElseThrow(()-> new NotFoundException("등록된 사용자가 없습니다."));
		if (!requestDto.getPassword().equals(user.getPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}
		String token = jwtUtil.createToken(user.getUsername(), UserRoleEnum.valueOf(user.getPassword()));
		return new LoginResponseDto(token);
	}
}
