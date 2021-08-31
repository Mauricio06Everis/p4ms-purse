package com.example.purse.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PurseKafkaListener {
    
    private final Logger log = LoggerFactory.getLogger(PurseKafkaListener.class);

    @KafkaListener(topics = "TEST_TOPIC", groupId = "FE_Dev")
    public void listenTestTopic(String data) {
        log.info("Consumed: " + data);
    }

}
