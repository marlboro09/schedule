package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequestDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
