package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private String _id;
    private String content;
    private String imageUrl;
    private String locationId;
    private String createdAt;
    private String updatedAt;
    private int commentCount;
    private int likeCount;
    private boolean isLiked;
}
