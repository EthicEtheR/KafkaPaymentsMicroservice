package com.aether.TransferService.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootStrap;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;
    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String idempotence;
    @Value("${spring.kafka.producer.properties.max.in.flight.per.connection}")
    private String maxInFlight;

    @Value("${deposit.money.topic}")
    private String depositTopic;
    @Value("${withdraw.money.topic}")
    private String withdrawTopic;

    @Value("${spring.kafka.producer.transaction-id-prefix}")
    private String transactionIdPrefix;



    @Bean
    Map<String,Object> producerConfigs(){
        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrap);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
        config.put(ProducerConfig.ACKS_CONFIG,acks);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,idempotence);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,maxInFlight);
        config.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,requestTimeout);
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,transactionIdPrefix);

        return config;
    }

    @Bean
    KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory<String,Object> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
    @Bean("kafkaTransactionManager")
    KafkaTransactionManager<String,Object> kafkaTransactionManager(ProducerFactory<String,Object> producerFactory){
        return new KafkaTransactionManager<>(producerFactory);
    }
    @Bean("transactionManager")
    JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Bean
    ProducerFactory<String,Object> producerFactory(){
        DefaultKafkaProducerFactory<String, Object> factory = new DefaultKafkaProducerFactory<>(producerConfigs());
        factory.setTransactionIdPrefix("transfer-service-" + UUID.randomUUID() + "-");
        return factory;
    }


    @Bean
    NewTopic createWithdrawTopic(){
        return TopicBuilder.name(withdrawTopic)
                .partitions(3)
                .replicas(3)
                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2")
                .build();
    }
    @Bean
    NewTopic createDepositTopic() {
        return TopicBuilder.name(depositTopic)
                .partitions(3)
                .replicas(3)
                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "2")
                .build();
    }


}
