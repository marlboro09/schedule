package com.sparta.schedule.dto.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestDto {
	private String title;

	private String description;

	private String date;
}
