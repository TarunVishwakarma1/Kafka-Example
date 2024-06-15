package com.delivery.controller;

import com.delivery.service.KafkaService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RequestMapping("/delete")
@RestController
public class DeleteRecordController {

    @Autowired
    private KafkaService service;

    @DeleteMapping("/{topicName}/{recordsToDelete}")
    public ResponseEntity<?> deleteRecords(@PathVariable String topicName, @PathVariable long recordsToDelete){
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        AdminClient adminClient = AdminClient.create(props);
        service.deleteRecords(topicName,recordsToDelete,adminClient);
        adminClient.close();
        return new ResponseEntity<>("Records have been deleted", HttpStatus.OK);
    }

}
