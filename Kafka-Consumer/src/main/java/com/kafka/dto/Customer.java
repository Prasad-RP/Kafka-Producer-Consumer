package com.kafka.dto;

import lombok.Data;

/**
 * The reason behind keeping this package outside of base package is, this DTO
 * is shared in between Producer and Consumer. While Consuming object is unable
 * to deserialize due to different package name. So to avoid this we kept same
 * package in both.
 * 
 * @author Prasad Pansare
 *
 */
@Data
public class Customer {

	private Long id;

	private String name;

	private String email;

}
