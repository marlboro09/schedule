package com.sparta.schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.requestdto.LoginRequestDto;
import com.sparta.schedule.dto.requestdto.UserRequestDto;
import com.sparta.schedule.dto.responsedto.LoginResponseDto;
import com.sparta.schedule.service.LoginService;
import com.sparta.schedule.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "사용자 관리", description = "사용자 관리 API입니다")
public class UserController {

	private final UserService userService;
	private final LoginService loginService;

	public UserController(UserService userService, LoginService loginService) {
		this.userService = userService;
		this.loginService = loginService;
	}

	@PostMapping("/user/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserRequestDto requestDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("회원가입 요청이 유효하지 않습니다.");
		}

		try {
			userService.register(requestDto);
			return ResponseEntity.ok("회원가입이 성공했습니다.");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/user/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
		try {
			LoginResponseDto responseDto = loginService.login(requestDto);
			return ResponseEntity.ok(responseDto);
		} catch (RuntimeException e) {
			return ResponseEntity.status(401).body(new LoginResponseDto(null, "로그인에 실패했습니다. " + e.getMessage()));
		}
	}
}