package com.phande.kafakademo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.phande.kafakademo.model.User;
@EnableKafka
@Configuration
public class KafkaConfiguration {
	@Value("${kafka.bootstrap-servers}")
     private String bootstrapServer;
	 private final  String GROUP_ID="group1";
	@Bean
	public ConsumerFactory<String, User> consumerFactory(){
		Map<String,Object> configProperties = new HashMap<>();
	      configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
	      configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	      configProperties.put(ConsumerConfig.GROUP_ID_CONFIG,GROUP_ID);
	      
		return new DefaultKafkaConsumerFactory<String, User>(configProperties,new StringDeserializer(),new JsonDeserializer<>(User.class));
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,User> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,User> listenerContainerFactory =new ConcurrentKafkaListenerContainerFactory<String,User>();
		listenerContainerFactory.setConsumerFactory(consumerFactory());
		return listenerContainerFactory;
	}

}
