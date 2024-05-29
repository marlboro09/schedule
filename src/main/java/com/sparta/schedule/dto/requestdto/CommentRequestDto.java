package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentRequestDto {

	private String content;

	@NotBlank
	private String userId;

	@NotBlank
	private Long scheduleId;
}