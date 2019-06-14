package com.epam.lab.mongo.MongoDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Friendship {

    private long friendId;
    private boolean accepted;
}