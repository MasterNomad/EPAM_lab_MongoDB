package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.Message;

import java.util.List;

public interface IMessageService {

    void sendMessage (long senderId, long receiverId, String text);

    List <Message> getUsersConversation (long firstUserId, long secondUserId);

}
