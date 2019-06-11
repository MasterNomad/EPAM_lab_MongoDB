package com.epam.lab.mongo.MongoDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Message {

    private Long senderId;
    private Long receiverId;
    private LocalDateTime receiveTime;
    private String text;

}
