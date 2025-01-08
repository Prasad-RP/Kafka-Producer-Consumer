package com.kafka.producer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessageToTopic(String msg) {

		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("spring-kafka-objects", msg);

		future.whenComplete((result, ex) -> {

			if (ex == null) {
				System.out.println(
						"Sent Message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {

				System.out.println("unable to Sent Message=[" + msg + "] due to: [" + ex.getMessage() + "]");
			}
		});
	}

	// To Publish Message To A Specific Partition
	public void sendMessageToTopic(String msg, Integer partition) {

		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-object-partition", partition,
				null, msg);

		future.whenComplete((result, ex) -> {

			if (ex == null) {
				System.out.println(
						"Sent Message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {

				System.out.println("unable to Sent Message=[" + msg + "] due to: [" + ex.getMessage() + "]");
			}
		});
	}

}
