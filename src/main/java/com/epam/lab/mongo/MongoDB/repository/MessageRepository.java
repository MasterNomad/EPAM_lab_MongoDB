package com.epam.lab.mongo.MongoDB.repository;

import com.epam.lab.mongo.MongoDB.dto.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findBySenderIdAndReceiverId(long senderId, long receiverId);

}
