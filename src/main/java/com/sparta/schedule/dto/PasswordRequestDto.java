package com.sparta.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(title = "Password 요청 DTO")
public class PasswordRequestDto {

	@NotNull
	@Schema(description = "스케줄 ID를 입력해주세요")
	private Long id;

	@NotBlank
	@Schema(description = "비밀번호를 입력해주세요")
	private String password;
}
