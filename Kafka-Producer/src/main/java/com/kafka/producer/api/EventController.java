package com.kafka.producer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.dto.Customer;
import com.kafka.producer.service.KafkaMessagePublisher;
import com.kafka.producer.service.KafkaObjectPublisher;

@RestController
@RequestMapping("/kafka-producer")
public class EventController {

	@Autowired
	private KafkaMessagePublisher kafkaMessagePublisher;

	@Autowired
	private KafkaObjectPublisher kafkaObjectPublisher;

	@GetMapping("/publish/{msg}")
	public ResponseEntity<?> publishMessage(@PathVariable String msg) {
		try {
			for (int i = 0; i <= 10000; i++) {
				kafkaMessagePublisher.sendMessageToTopic(msg + " " + i);
			}
			return ResponseEntity.ok("Mssage Published.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/publish")
	public ResponseEntity<?> publishObject(@RequestBody Customer customer) {
		try {
			kafkaObjectPublisher.sendObjectsToTopic(customer);
			return ResponseEntity.ok("Object Published.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// To Publish Message To A Specific Partition
	@GetMapping("/publish/{msg}/partition/{partition}")
	public ResponseEntity<?> publishMessage(@PathVariable String msg, @PathVariable Integer partition) {
		try {
			kafkaMessagePublisher.sendMessageToTopic(msg, partition);
			return ResponseEntity.ok("Mssage Published.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
