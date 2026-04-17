package com.codingshuttle.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

//@FieldDefaults(level = AccessLevel.PRIVATE) we can use this rather than making
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
//postgres already has user table so we need to rename this or it will give error
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;

    //for date we have two things we have LocalDateTime and Instant
    //both are kind of same thing but with instant we get more functionality
    // private LocalDateTime createdAt;
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
    private Instant deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    //why we are doing everything private
    //because this user can be only be updated using constructor or setter
    //this is called encapsulation
}
