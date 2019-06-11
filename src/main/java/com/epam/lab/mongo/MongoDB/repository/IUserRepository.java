package com.epam.lab.mongo.MongoDB.repository;

import com.epam.lab.mongo.MongoDB.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, Long> {
}
