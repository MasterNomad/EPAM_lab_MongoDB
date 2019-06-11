package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.Message;
import com.epam.lab.mongo.MongoDB.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository messageRepository;

    @PostConstruct
    public void demoData() {
        messageRepository.deleteAll();
        sendMessage(1, 2, "Hello");
        sendMessage(2, 1, "Hi! :))))");
        sendMessage(1, 2, "Goodbye");

        System.out.println(getUsersConversation(1, 2));
    }

    @Override
    public void sendMessage(long senderId, long receiverId, String text) {
        messageRepository.save(new Message(senderId, receiverId, LocalDateTime.now(), text));
    }

    @Override
    public List<Message> getUsersConversation(long firstUserId, long secondUserId) {
        Message firstExample = new Message(firstUserId, secondUserId, null, null);
        Message secondExample = new Message(secondUserId, firstUserId, null, null);
        List<Message> messages = messageRepository.findAll(Example.of(firstExample));
        messages.addAll(messageRepository.findAll(Example.of(secondExample)));
        messages.sort(Comparator.comparing(Message::getReceiveTime));
        return messages;
    }


}
