package com.sparta.schedule.dto.requestdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

	@NotEmpty
	private String nickname;

	@NotEmpty
	@Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username은 4자 이상, 10자 이하이며 알파벳 소문자(a~z)와 숫자(0~9)로 구성되어야 합니다.")
	private String username;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "password는 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z)와 숫자(0~9)로 구성되어야 합니다.")
	private String password;

	private boolean admin;
	private String adminToken;

	public boolean isAdmin() {
		return admin;
	}

	public String getAdminToken() {
		return adminToken;
	}
}