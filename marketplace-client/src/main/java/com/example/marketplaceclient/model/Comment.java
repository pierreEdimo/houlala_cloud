package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
    private String user;
    private String content;
    private String postId;
    private String _id;
    private String createdAt;
    private String updatedAt;
}
