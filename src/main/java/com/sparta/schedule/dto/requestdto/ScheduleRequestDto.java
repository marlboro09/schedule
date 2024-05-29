package com.sparta.schedule.dto.requestdto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ScheduleRequestDto {

	private String title;

	private String description;

	@NotEmpty
	private String manager;

	@NotEmpty
	private String password;

	private LocalDateTime createdAt;

	public String getContent() {
		return null;
	}

	public LocalDateTime getCreatedDate() {
		return null;
	}
}