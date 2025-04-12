package com.volpe.produtor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volpe.produtor.dto.MensagemDTO;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutorService {

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageToTopic(@Nonnull final MensagemDTO message, @Nonnull final Integer partitionId) throws JsonProcessingException {

        MessageBuilder<String> messageBuilder = MessageBuilder
                .withPayload(new ObjectMapper().writeValueAsString(message))
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString());

        Optional.of(partitionId)
                .ifPresent(id -> messageBuilder.setHeader(KafkaHeaders.RECEIVED_PARTITION, id));

        Message<String> messageForKafka = messageBuilder.build();

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(messageForKafka);

        future.whenComplete(new BiConsumer<SendResult<String, String>, Throwable>() {
            @Override
            public void accept(SendResult<String, String> stringStringSendResult, Throwable throwable) {
                if (throwable != null) {
                    log.error("Error sending message: {}", throwable.getMessage());
                } else {
                    log.info("Message sent successfully: {}, {}", stringStringSendResult.getProducerRecord().key(), stringStringSendResult.getProducerRecord().value());
                }
            }
        });
    }
}
