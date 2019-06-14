package com.epam.lab.mongo.MongoDB.controllers;

import com.epam.lab.mongo.MongoDB.dto.Message;
import com.epam.lab.mongo.MongoDB.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping("/sendMessage")
    public void sendMessage(long senderId, long receiverId, String text) {
        messageService.sendMessage(new Message(senderId, receiverId, LocalDateTime.now(), text));
    }

    @GetMapping("/getUsersConversation")
    public List<Message> getAllUsers(@RequestParam long firstId, @RequestParam long secondId) {
        return messageService.getUsersConversation(firstId, secondId);
    }

}
