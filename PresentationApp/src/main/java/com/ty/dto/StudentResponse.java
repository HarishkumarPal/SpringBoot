package com.ty.dto;

import com.ty.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

	private String name;

	private String email;

	private Long phone;

	private Status status;

	private Double userTotalScore;
}
