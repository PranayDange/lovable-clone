package com.codingshuttle.projects.lovable_clone.entity;

import com.codingshuttle.projects.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId //we cannot use @Id here because here we are using composite id here
    ProjectMemberId id;
    //what will this do is this will take all the fields from the ProjectMemberId it will make primary key
    //out of this two composite keys present in the ProjectMemberId...will make
    //primary key out of this composite keys


    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;
    Instant invitedAt;
    Instant acceptedAt;
}
