package com.sparta.schedule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.LoginRequestDto;
import com.sparta.schedule.dto.SignupRequestDto;
import com.sparta.schedule.service.CommentService;
import com.sparta.schedule.service.UserService;
import com.sparta.security.UserDetailsImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "회원 관리", description = "회원 가입 및 로그인")
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	private final CommentService commentService;

	public UserController(UserService userService, CommentService commentService) {
		this.userService = userService;
        this.commentService = commentService;
	}

	@PostMapping("/user/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if(fieldErrors.size() > 0) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + "필드 :" + fieldError.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입에 실패했습니다");
		}
		userService.signup(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다");
	}

	@PostMapping("/user/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto, BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if(fieldErrors.size() > 0) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + "필드 :" + fieldError.getDefaultMessage());
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인에 실패했습니다");
		}
		userService.login(requestDto);
		return ResponseEntity.status(HttpStatus.OK).body("로그인이 완료되었습니다");
	}


	@GetMapping("/user-comment")
	public String getUserInfo(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		model.addAttribute("comment", commentService.getComment(userDetails.getUser()));
		return "user-comment";
	}
}
