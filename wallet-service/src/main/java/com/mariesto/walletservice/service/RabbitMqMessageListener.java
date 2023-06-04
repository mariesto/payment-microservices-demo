package com.mariesto.walletservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqMessageListener {

  @RabbitListener(queues = "wallet")
  public void onMessage(WalletMessage walletMessage) {
    log.info("[x] consume message : {}", walletMessage);
//    String stringMessage = new String(message.getBody(), StandardCharsets.UTF_8);
//    ObjectMapper objectMapper = new ObjectMapper();
//    try {
//      WalletMessage messageResult = objectMapper.readValue(stringMessage, WalletMessage.class);
//      log.info(" [x] Message Received : {}", messageResult);
//    } catch (JsonProcessingException e) {
//      throw new RuntimeException(e);
//    }
  }
}
