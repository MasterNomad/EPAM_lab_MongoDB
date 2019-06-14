package com.epam.lab.mongo.MongoDB.repository;

import com.epam.lab.mongo.MongoDB.dto.Friendship;
import com.epam.lab.mongo.MongoDB.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByFriendsLike(Friendship friendship);

    User findFirstByOrderByRatingDesc();

}
