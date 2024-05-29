package com.sparta.schedule.dto.responsedto;

import lombok.Data;
import lombok.Getter;

@Getter

@Data
public class LoginResponseDto {

	private String token;

	private String message;
}
