package com.solera.audamedic.operativa.msprocesamiento;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor;


@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static class RejectAndDontRequeueRecoverer implements MessageRecoverer {
        @Override
        public void recover(Message message, Throwable cause) {
            throw new AmqpRejectAndDontRequeueException(cause);
        }
    }

    @Bean
    public StatefulRetryOperationsInterceptor interceptor() {
        return RetryInterceptorBuilder
                .stateful()
                .maxAttempts(5)
                .backOffOptions(1000, 3.0, 10000) // initialInterval, multiplier, maxInterval
                .recoverer(new RejectAndDontRequeueRecoverer())  // This is the custom logic after retries are exhausted
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setAdviceChain(interceptor());
        return factory;
    }
}
