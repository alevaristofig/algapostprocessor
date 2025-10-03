package com.postprocessorservice.domain.service;

import org.springframework.stereotype.Service;

import com.postprocessorservice.api.model.PostListener;
import com.postprocessorservice.api.model.PostOutuptListener;

@Service
public class PostProcessorService {

	public void processarPost(PostListener post, String postId) {
		String[] words = post.getBody().trim().split("\\s+");
		float calculatedValue = (float) (words.length * 0.10);
		
		PostOutuptListener postOutput = new PostOutuptListener();
		
		postOutput.setPostId(postId);
		postOutput.setWordCount(words.length);
		postOutput.setCalculatedValue(calculatedValue);
		
		System.out.println(words.length+","+calculatedValue);
	}
}
