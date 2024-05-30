package com.sparta.schedule.dto.responsedto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {

	private String token;

	private String message;

	public LoginResponseDto(String token, String message) {
		this.token = token;
		this.message = message;
	}
}
