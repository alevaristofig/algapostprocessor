package com.postprocessorservice.api.model;

import lombok.Data;

@Data
public class PostOutuptListener {

	private String postId;
	private Integer wordCount;
	private Float calculatedValue;
}
