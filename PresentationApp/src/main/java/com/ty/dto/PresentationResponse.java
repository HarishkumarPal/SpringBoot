package com.ty.dto;

import java.time.LocalDateTime;

import com.ty.entity.User;
import com.ty.enums.PresentationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresentationResponse {
	private String course;

	private String topic;

	private PresentationStatus presentationstatus;

	private Double userTotalScorce;
	
	private LocalDateTime createdDate;
	
}
