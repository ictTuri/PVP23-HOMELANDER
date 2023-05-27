package com.codeonmars.notificationsms.consumer;

import com.codeonmars.notificationsms.model.notifications.dto.NotificationRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class NotificationsListener {

    private static final Logger logger = LogManager.getLogger(NotificationsListener.class);
    @KafkaListener(topics = "notifications", groupId = "group_one", containerFactory="kafkaListenerContainerFactory")
    void listener(@Payload NotificationRecord data, @Headers MessageHeaders headers){
        logger.info("this is the payload: {}", data);
    }
}
