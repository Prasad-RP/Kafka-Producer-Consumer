package com.kafka.consumer.service;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * To Listen {@link String} messages.
 * 
 * @author Prasad Pansare
 *
 */
@Service
@Slf4j
public class KafkaMessageListener {

	@KafkaListener(topics = "spring-kafka-tp2", groupId = "ex-group-2")
	public void firstConsumer(String msg) {
		System.out.println("Consumer 1 Consume the message: " + msg);
	}

	// It's not a good practice to keep all of these methods in same file, use
	// different classes.
	@KafkaListener(topics = "spring-kafka-tp2", groupId = "ex-group-2")
	public void secondConsumer(String msg) {
		System.out.println("Consumer 2 Consume the message: " + msg);
	}

	@KafkaListener(topics = "spring-kafka-tp2", groupId = "ex-group-2")
	public void thirdConsumer(String msg) {
		System.out.println("Consumer 3 Consume the message: " + msg);
	}

	@KafkaListener(topics = "spring-kafka-tp2", groupId = "ex-group-2")
	public void fourthConsumer(String msg) {
		System.out.println("Consumer 4 Consume the message: " + msg);
	}

	// To Consume Message From A Specific Partition
	@KafkaListener(topics = "kafka-object-partition", groupId = "partition-group-1", topicPartitions = {
			@TopicPartition(topic = "kafka-object-partition", partitions = { "2" }) })
	public void partitionConsumer(String msg) {
		System.out.println("Partition 1 Consumer Consume the message: " + msg);
	}

	// This will retry for 3 times and then it will move the msg into dead letter
	@RetryableTopic(attempts = "4") // 3 topic N-1
	@KafkaListener(topics = "kafka-error-handler", groupId = "ex-group-error")
	public void deadLetterConsumer(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.OFFSET) long offset) throws Exception {
		System.out.println("Consumer For Error Consume the message: " + msg);
		if (msg.equalsIgnoreCase("error"))
			throw new Exception("Unsupported Message");
	}

	// Method to handle dead letters
	@DltHandler
	public void listenDLT(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.OFFSET) long offset) {
		log.info("DLT Received : {} , from {} , offset {}", msg, topic, offset);
	}

}
