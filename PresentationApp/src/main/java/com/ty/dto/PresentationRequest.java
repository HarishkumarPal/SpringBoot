package com.ty.dto;

import com.ty.enums.PresentationStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresentationRequest {

	private String course;

	private String topic;

	private PresentationStatus presentationstatus;

}
