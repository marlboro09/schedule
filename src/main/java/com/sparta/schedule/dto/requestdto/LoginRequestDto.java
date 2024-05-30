package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
}
