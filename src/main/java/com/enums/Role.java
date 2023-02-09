package com.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

	ADMIN(0, "ROLE_ADMIN"), GENERAL(1, "ROLE_GENERAL");
	private int code;
	private String value;
	
	static public String getValueByCode(int code) {
		return Arrays.stream(values()).filter(role -> {
			return role.code == code;
		}).findFirst().orElseThrow(IllegalArgumentException::new).getValue();
	} 

}
