package com.enduser;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener(topics=Constants.LOCATION_UPDATE_TOPIC, groupId=Constants.GROUP_ID)
    public void updatedLocation(ConsumerRecord<String, String> record) {
        // Access the value of the message
        String value = record.value();
        System.out.println("Value: " + value);

        // Access the key of the message
        String key = record.key();
        System.out.println("Key: " + key);

        // Access the topic of the message
        String topic = record.topic();
        System.out.println("Topic: " + topic);

        // Access the partition of the message
        int partition = record.partition();
        System.out.println("Partition: " + partition);

        // Access the offset of the message
        long offset = record.offset();
        System.out.println("Offset: " + offset);

        // Access the headers of the message
        Headers headers = record.headers();
        System.out.println("Headers: " + headers);

        // Access the timestamp of the message
        long timestamp = record.timestamp();
        System.out.println("Timestamp: " + timestamp);
    }
}
