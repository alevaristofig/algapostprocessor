package com.postprocessorservice.infrastruct.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

	private static final String TEXT_PROCESSOR = "text-processor-service.post-processing.v1";
	public static final String FANOUT_TEXT_PROCESSOR = TEXT_PROCESSOR +".e";
	public static final String QUEUE_TEXT_PROCESSOR = TEXT_PROCESSOR +".q";
	public static final String QUEUE_TEXT_PROCESSOR_POST = "post-service.post-processing-result.v1.q";
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper mapper) {
		return new Jackson2JsonMessageConverter(mapper);
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
		return new RabbitAdmin(factory);
	}
	
	@Bean
	public FanoutExchange exchange() {
		return ExchangeBuilder.fanoutExchange(FANOUT_TEXT_PROCESSOR).build();
	}
	
	@Bean
	public Queue queuePost() {
		return QueueBuilder.durable(QUEUE_TEXT_PROCESSOR).build();
	}
	
	@Bean
	public Queue queueProcessorPost() {
		return QueueBuilder.durable(QUEUE_TEXT_PROCESSOR_POST).build();
	}
	
	@Bean
	public Binding bindingText() {
		return BindingBuilder.bind(queuePost()).to(exchange());
	}
	
	@Bean
	public Binding bindingTextPost() {
		return BindingBuilder.bind(queueProcessorPost()).to(exchange());
	}
}
