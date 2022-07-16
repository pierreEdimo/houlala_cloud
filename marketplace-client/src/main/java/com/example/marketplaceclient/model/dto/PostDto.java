package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String _id;
    private String content;
    private String imageUrl;
    private Location location;
    private String createdAt;
    private String updatedAt;
    private int commentCount;
    private int likeCount;
    private boolean isLiked;

    public PostDto() {}

    public PostDto(String _id,
                   String content,
                   String imageUrl,
                   Location location,
                   String createdAt,
                   String updatedAt,
                   int commentCount,
                   int likeCount,
                   boolean isLiked
                   ){
        this._id = _id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.isLiked = isLiked;
    }
}
