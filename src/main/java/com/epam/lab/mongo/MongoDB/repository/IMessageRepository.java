package com.epam.lab.mongo.MongoDB.repository;

import com.epam.lab.mongo.MongoDB.dto.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMessageRepository extends MongoRepository<Message, String> {
}
