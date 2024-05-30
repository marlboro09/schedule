package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {

	@NotNull(message = "스케줄 ID는 빈 칸이 될 수 없습니다.")
	private Long scheduleId;

	@NotNull(message = "내용은 빈 칸이 될 수 없습니다.")
	private String description;
}