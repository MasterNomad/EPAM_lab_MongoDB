package com.epam.lab.mongo.MongoDB.services;

import com.epam.lab.mongo.MongoDB.dto.User;
import com.epam.lab.mongo.MongoDB.exceptions.NoSuchUserException;
import com.epam.lab.mongo.MongoDB.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @PostConstruct
    public void demoData() {
        userRepository.deleteAll();
        saveUser(new User(userRepository.count() + 1, "Heidi Bennett", LocalDate.now().minusYears(23)));
        saveUser(new User(userRepository.count() + 1, "Lauren Rice ", LocalDate.now().minusYears(35).minusDays(65)));
        saveUser(new User(userRepository.count() + 1, "Sally Roy", LocalDate.now().minusYears(18).minusDays(128)));
        saveUser(new User(userRepository.count() + 1, "Lyle Olson ", LocalDate.now().minusYears(28).minusDays(256)));

        addFriend(1, 2);
        addFriend(2, 1);
        addFriend(2, 3);
        addFriend(3, 1);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addFriend(long hostUserId, long friendUserId) {
        User hostUser = getUserById(hostUserId);
        User friendUser = getUserById(friendUserId);

        Map<Long, Boolean> hostFriends = hostUser.getFriends();
        Set<Long> hostRequests = hostUser.getRequests();
        Map<Long, Boolean> friendsFriends = friendUser.getFriends();
        Set<Long> friendRequests = friendUser.getRequests();

        if (hostRequests.contains(friendUserId)) {
            hostFriends.put(friendUserId, true);
            friendsFriends.put(hostUserId, true);
            hostRequests.remove(friendUserId);
        } else {
            hostFriends.put(friendUserId, false);
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
        User user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new NoSuchUserException();
        }
        return user;
    }

    @Override
    public List<User> getUsersWithFriend(long friendId) {
        User user = new User();
        HashMap<Long, Boolean> friends = new HashMap<>();
        friends.put(friendId, true);
        user.setFriends(friends);
        return userRepository.findAll(Example.of(user));
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
