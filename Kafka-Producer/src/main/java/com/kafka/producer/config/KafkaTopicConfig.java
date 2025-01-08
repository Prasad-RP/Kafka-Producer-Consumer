package com.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	/**
	 * Creates a new Kafka topic with the specified name, number of partitions, and
	 * replication factor.
	 * 
	 * @return NewTopic object representing the Kafka topic.
	 */
	@Bean
	public NewTopic accountConfirmationTopic() {
		return TopicBuilder.name("kafka-topic-2").build();
	}
}
