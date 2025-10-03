package com.postprocessorservice.domain.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;

import com.postprocessorservice.api.model.PostListener;
import com.postprocessorservice.infrastruct.rabbitmq.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ListenerRabbitService {

	//@Autowired
	//private 
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_TEXT_PROCESSOR)
	public void listenerPost(PostListener post, @Header(value = "postId") String postId) {
		
		System.out.println(postId+","+post.toString());
	}
}
