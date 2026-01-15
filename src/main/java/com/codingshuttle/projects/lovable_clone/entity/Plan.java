package com.codingshuttle.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {
    Long id;
    String name;
    String stripePriceId;
    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews;//maximum number of previews available per plan
    Boolean unlimitedAi;//unlimited access to LLM,ignore maxTokensPerDay if true
    Boolean active;

    //we are using wrapper classes because if this for ex.maxProjects it is not defined somewhere
    //then this things become null.when you put this things in database then it becomes null
    //if you put int there and you did not define int.and if you try putting this in database you will get error

}
