package com.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

/**
 * To Listen {@link String} messages.
 * 
 * @author Prasad Pansare
 *
 */
@Service
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

}
