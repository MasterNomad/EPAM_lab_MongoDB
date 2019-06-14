package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.Message;
import com.epam.lab.mongo.MongoDB.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @PostConstruct
    public void demoData() {
        messageRepository.deleteAll();
        sendMessage(new Message(1L, 2L, LocalDateTime.now(), "Hello"));
        sendMessage(new Message(2L, 1L, LocalDateTime.now(), "Hi! :))))"));
        sendMessage(new Message(1L, 2L, LocalDateTime.now(),"Goodbye"));
    }

    @Override
    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getUsersConversation(long firstUserId, long secondUserId) {
        List<Message> messages = messageRepository.findBySenderIdAndReceiverId(firstUserId, secondUserId);
        messages.addAll( messageRepository.findBySenderIdAndReceiverId(secondUserId, firstUserId));
        messages.sort(Comparator.comparing(Message::getReceiveTime));
        return messages;
    }

}
