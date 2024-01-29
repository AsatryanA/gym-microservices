package com.epam.gym.config;

import com.epam.gym.model.dto.TrainerSummaryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ActiveMQConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        var activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setUseAsyncSend(true);
        return activeMQConnectionFactory;
    }

@Bean
public MessageConverter converter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper(objectMapper());
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("path");
    Map<String,Class<?>> map = new HashMap<>();
    map.put("TrainerSummaryDto", TrainerSummaryDto.class);
    converter.setTypeIdMappings(map);
    return converter;
}
    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}