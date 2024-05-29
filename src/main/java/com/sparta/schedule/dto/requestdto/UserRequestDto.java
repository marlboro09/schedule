package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@Data
public class UserRequestDto {

	@NotBlank
	private String nickname;

	@NotBlank
	@Size(min = 4, max = 10)
	@Pattern(regexp = "^[a-z0-9]+$")
	private String username;

	@NotBlank
	@Size(min = 8, max = 15)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;
}