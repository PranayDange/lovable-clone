package com.codingshuttle.projects.lovable_clone.dto.auth;

public record AuthResponse(String token, UserProfileResponse user) {
}

//record --record is a special type of class when you make something as a record all the feilds
//become immutable that mean those are private and final you get getter constructor
//you don't get setter and all args constructor
