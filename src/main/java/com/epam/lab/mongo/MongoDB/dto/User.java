package com.epam.lab.mongo.MongoDB.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class User {

    @Id
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Set<Friendship> friends = new HashSet<>();
    private Set<Long> requests = new HashSet<>();
    private int rating;

    public User() {
    }

    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(Long id, String name, LocalDate birthDate, int rating) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.rating = rating;
    }
}
