package com.mariesto.walletservice.config;

import com.mariesto.walletservice.service.RabbitMqMessageListener;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMqConfig {

  private final RabbitMqConfigProps rabbitMqConfigProps;

  public RabbitMqConfig(RabbitMqConfigProps rabbitMqConfigProps) {
    this.rabbitMqConfigProps = rabbitMqConfigProps;
  }


  @Bean
  public ConnectionFactory amqpConnectionFactory() {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("rabbit-mq");
    log.debug("try to initialize connection factory : {}", connectionFactory);
    return connectionFactory;
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    return new CachingConnectionFactory(amqpConnectionFactory());
  }

  @Bean
  public AmqpTemplate amqpTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

  @Bean
  public MessageConverter converter(){
    return new Jackson2JsonMessageConverter();
  }

//  @Bean
//  public SimpleMessageListenerContainer listenerContainer() {
//    final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(
//        connectionFactory());
//    listenerContainer.setQueues(queue());
//    listenerContainer.setMessageListener(new RabbitMqMessageListener());
//    listenerContainer.setMessagePropertiesConverter(Mes);
//    return listenerContainer;
//  }

  @Bean
  public Queue queue() {
    return new Queue(rabbitMqConfigProps.getQueueName(), rabbitMqConfigProps.isDurable(),
        rabbitMqConfigProps.isExclusive(),
        rabbitMqConfigProps.isAutoDelete());
  }
}
