package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.User;

import java.util.List;

public interface IUserService {

    User saveUser (User user);

    void addFriend (long hostUserId, long friendUserId);

    User getUserById (long id);

    User getUserWithMaxRating();

    List <User> getUsersWithFriend (long friendId);

    List <User> getAllUsers();

    List<User> findUserFriends(long userId);

}
