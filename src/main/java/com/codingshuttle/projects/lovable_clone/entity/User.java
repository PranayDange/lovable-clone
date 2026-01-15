package com.codingshuttle.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

//@FieldDefaults(level = AccessLevel.PRIVATE) we can use this rather than making
@Getter
@Setter
public class User {
    private Long id;
    private String email;
    private String passwordHash;
    private String name;
    private String avatarUrl;

    //for date we have two things we have LocalDateTime and Instant
    //both are kind of same thing but with instant we get more functionality
    // private LocalDateTime createdAt;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    //why we are doing everything private
    //because this user can be only be updated using constructor or setter
    //this is called encapsulation
}
