package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.Friendship;
import com.epam.lab.mongo.MongoDB.dto.User;
import com.epam.lab.mongo.MongoDB.exceptions.NoSuchUserException;
import com.epam.lab.mongo.MongoDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void demoData() {
        Random random = new Random();

        userRepository.deleteAll();
        saveUser(new User(userRepository.count() + 1, "Heidi Bennett",
                LocalDate.now().minusYears(23), random.nextInt(100)));
        saveUser(new User(userRepository.count() + 1, "Lauren Rice ",
                LocalDate.now().minusYears(35).minusDays(65), random.nextInt(100)));
        saveUser(new User(userRepository.count() + 1, "Sally Roy",
                LocalDate.now().minusYears(18).minusDays(128), random.nextInt(100)));
        saveUser(new User(userRepository.count() + 1, "Lyle Olson ",
                LocalDate.now().minusYears(28).minusDays(256), random.nextInt(100)));

        addFriend(1, 2);
        addFriend(2, 1);
        addFriend(2, 3);
        addFriend(3, 1);
        addFriend(1, 3);
        addFriend(4, 3);
    }

    @Override
    public User saveUser(User user) {
        user.setId(userRepository.count() + 1);
        return userRepository.save(user);
    }

    @Override
    public void addFriend(long hostUserId, long friendUserId) {
        User hostUser = getUserById(hostUserId);
        User friendUser = getUserById(friendUserId);

        Set<Friendship> hostFriends = hostUser.getFriends();
        Set<Long> hostRequests = hostUser.getRequests();

        Set<Friendship> friendsFriends = friendUser.getFriends();
        Set<Long> friendRequests = friendUser.getRequests();

        if (hostRequests.contains(friendUserId)) {
            hostFriends.add(new Friendship(friendUserId, true));
            friendsFriends.remove(new Friendship(hostUserId, false));
            friendsFriends.add(new Friendship(hostUserId, true));
            hostRequests.remove(friendUserId);
        } else {
            hostFriends.add(new Friendship(friendUserId, false));
            friendRequests.add(hostUserId);
        }

        hostUser.setFriends(hostFriends);
        hostUser.setRequests(hostRequests);
        userRepository.save(hostUser);

        friendUser.setFriends(friendsFriends);
        friendUser.setRequests(friendRequests);
        userRepository.save(friendUser);
    }


    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(NoSuchUserException::new);
    }

    @Override
    public User getUserWithMaxRating() {
        return userRepository.findFirstByOrderByRatingDesc();
    }

    @Override
    public List<User> getUsersWithFriend(long friendId) {
        return userRepository.findByFriendsLike(new Friendship(friendId, true));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserFriends(long userId) {
        return null;
    }


}
