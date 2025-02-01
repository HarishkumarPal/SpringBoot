package com.ty.dto;

import com.ty.enums.Role;
import com.ty.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private String name;

	private String email;

	private Long phone;

	private Status status;

	private Double userTotalScore;

	private Role role;
}
