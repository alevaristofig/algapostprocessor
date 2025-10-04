package com.postprocessorservice.domain.service;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.postprocessorservice.api.model.PostOutuptListener;
import com.postprocessorservice.infrastruct.rabbitmq.RabbitMQConfig;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

	private RabbitTemplate rabbitTemplate;
	
	public void notificar(PostOutuptListener post) {
				
		rabbitTemplate.convertAndSend("",RabbitMQConfig.QUEUE_TEXT_PROCESSOR_POST,post);
		
		log.info(String.format("Registrou a mensagem %s ", post.toString()));
	}
}
