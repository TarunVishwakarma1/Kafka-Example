package com.delivery.service;

import com.delivery.config.Constants;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DeleteRecordsResult;
import org.apache.kafka.clients.admin.RecordsToDelete;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class KafkaService {

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public boolean updateLocation(String location){
        this.kafkaTemplate.send(Constants.LOCATION_TOPIC_NAME, location);
        System.out.println(location);
        return true;
    }

    public boolean deleteRecords(String topicName, long records,AdminClient adminClient){

        // Specify the topic partition
        TopicPartition topicPartition = new TopicPartition(topicName, 0);

        // Create a map with the topic partition and the records to delete before offset 10
        Map<TopicPartition, RecordsToDelete> recordsToDelete = new HashMap<>();
        recordsToDelete.put(topicPartition, RecordsToDelete.beforeOffset(records));

        // Delete the records and handle the result
        DeleteRecordsResult result = adminClient.deleteRecords(recordsToDelete);
        result.all().whenComplete((voidResult, throwable) -> {
            if (throwable == null) {
                System.out.println("Records deleted successfully.");
            } else {
                throwable.printStackTrace();
            }
        });

        return true;
    }
}
