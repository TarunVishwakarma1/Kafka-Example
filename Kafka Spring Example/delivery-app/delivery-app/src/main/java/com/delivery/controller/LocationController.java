package com.delivery.controller;

import com.delivery.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(){
        for(int i=1;i<=10;i++){
            kafkaService.updateLocation("{ Latitude: "+ Math.round(Math.random()*100)+",\n Longitude: "+Math.round(Math.random()*100)+"}");
            System.out.println(i+"\n_________________");
        }
        return new ResponseEntity<>(Map.of("Message","Location updated"), HttpStatus.OK);
    }

}
