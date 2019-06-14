package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.Message;

import java.util.List;

public interface IMessageService {

    void sendMessage(Message message);

    List <Message> getUsersConversation (long firstUserId, long secondUserId);

}
