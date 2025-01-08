package com.kafka.producer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.kafka.dto.Customer;

@Service
public class KafkaObjectPublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendObjectsToTopic(Customer customer) {

		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-objects", customer);

		future.whenComplete((result, ex) -> {

			if (ex == null) {
				System.out.println("Sent Message=[" + customer.toString() + "] with offset=["
						+ result.getRecordMetadata().offset() + "]");
			} else {
				ex.printStackTrace();
				System.out.println(
						"unable to Sent Message=[" + customer.toString() + "] due to: [" + ex.getMessage() + "]");
			}
		});
	}

}
