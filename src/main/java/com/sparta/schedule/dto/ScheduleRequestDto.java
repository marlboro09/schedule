package com.sparta.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "Schedule 요청 DTO")
public class ScheduleRequestDto {

	@Schema(description = "제목을 입력해주세요")
	private String title;

	@Schema(description = "내용을 입력해주세요")
	private String contents;

	@Schema(description = "이름을 입력해주세요")
	private String name;

	@Schema(description = "비밀번호를 입력해주세요")
	private String password;

	@Schema(description = "날짜를 입력해주세요")
	private String date;
}
