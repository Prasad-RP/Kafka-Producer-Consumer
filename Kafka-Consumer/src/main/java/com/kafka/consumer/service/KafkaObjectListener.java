package com.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.dto.Customer;

/**
 * To Listen {@link Object} messages.
 * 
 * @author Prasad Pansare
 *
 */
@Service
public class KafkaObjectListener {

	/**
	 * 
	 * Spring does not directly inject @Value-annotated fields into @KafkaListener
	 * topics. You'll need to explicitly resolve the property value and pass it to
	 * the listener.
	 * 
	 * {@topicName}: Spring Expression Language (SpEL) is used to resolve the
	 * property value at runtime. This ensures that the topicName defined in your
	 * application.properties file is properly resolved.
	 * 
	 * @param customer
	 */
	@KafkaListener(topics = "kafka-topic-2", groupId = "ex-group-4", containerFactory = "containerFactory")
	public void firstConsumer(Customer customer) {
		System.out.println("Consumer Consume the message: " + customer.toString());
	}
}
