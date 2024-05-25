package com.sparta.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(title = "Update 요청 DTO")
public class UpdateRequestDto {

	@NotNull
	@Schema(description = "스케줄 ID를 입력해주세요")
	private Long id;

	@NotBlank
	@Schema(description = "비밀번호를 입력해주세요")
	private String password;

	@Schema(description = "제목을 입력해주세요")
	private String title;

	@Schema(description = "내용을 입력해주세요")
	private String contents;

	@Schema(description = "이름을 입력해주세요")
	private String name;

	@Schema(description = "날짜를 입력해주세요")
	private String date;
}
