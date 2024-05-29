package com.sparta.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import lombok.RequiredArgsConstructor;

@Tag(name = "사용자 관리", description = "사용자 관리 API입니다")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final LoginService loginService;

	@PostMapping("/users/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserRequestDto requestDto) {
		loginService.register(requestDto);
		return ResponseEntity.ok("회원가입이 성공했습니다.");
	}

	@PostMapping("/users/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
		return ResponseEntity.ok(loginService.login(requestDto));
	}
}