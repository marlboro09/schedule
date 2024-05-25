package com.sparta.schedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(title = "댓글을 달아주세요")
public class CommentRequestDto {
	@NotNull
	private String contents;

	@NotNull
	private String name;

	@NotNull
	private Long scheduleId;
}
