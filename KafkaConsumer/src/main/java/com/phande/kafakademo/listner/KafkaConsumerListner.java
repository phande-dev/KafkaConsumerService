package com.phande.kafakademo.listner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.phande.kafakademo.model.User;

@Service
public class KafkaConsumerListner {
	 private final Logger log = LoggerFactory.getLogger(KafkaConsumerListner.class);
	@KafkaListener(topics = "user-topic",groupId = "group1",containerFactory = "kafkaListenerContainerFactory")
	public void consume(User user) {
		log.info("Consumed user : {}", user);
	}

}
