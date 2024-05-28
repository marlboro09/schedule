package com.sparta.schedule.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
	@NotBlank
	private String nickname;

	@NotBlank
	@Size(min = 4, max = 10)
	@Pattern(regexp = "^[a-z0-9]")
	private String username;

	@NotBlank
	@Size(min = 8, max = 15)
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String password;

	@NotBlank
	private boolean admin = false;
	private String adminToken = "";

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createtime;
}
