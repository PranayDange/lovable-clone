package com.codingshuttle.projects.lovable_clone.entity;

import com.codingshuttle.projects.lovable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    Long id;
    ChatSession chatSession;
    String content;
    MessageRole role;
    String toolCalls;
    Integer tokenUsed;
    Integer createdAt;
}
