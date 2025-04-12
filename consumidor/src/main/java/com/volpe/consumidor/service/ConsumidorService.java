package com.volpe.consumidor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volpe.consumidor.dto.MensagemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumidorService {

    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${kafka.group.id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"0", "1", "2"})},
            containerFactory = "listenerContainerFactory",
            clientIdPrefix = "firstTopic")
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {

        MensagemDTO dto = new ObjectMapper().readValue(message, MensagemDTO.class);

        log.info("consume: Received message -> {}, {}, {}", key, dto, offset);
    }

    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${kafka.group.id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"1"})},
            containerFactory = "listenerContainerFactory",
            clientIdPrefix = "firstTopic")
    public void consumeOnePartition(@Payload String message, @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                    @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {

        MensagemDTO dto = new ObjectMapper().readValue(message, MensagemDTO.class);

        log.info("consumeOnePartition: Received message -> {}, {}, {}", key, dto, offset);
    }


}
