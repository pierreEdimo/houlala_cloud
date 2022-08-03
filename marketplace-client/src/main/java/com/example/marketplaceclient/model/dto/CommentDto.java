package com.example.marketplaceclient.model.dto;

import com.example.marketplaceclient.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String content;
    private String _id;
    private String createdAt;
    private String updatedAt;
    private String postId;

    private User author;

    public CommentDto(
            String content,
            String _id,
            String createdAt,
            String updatedAt,
            String postId,
            User author
    ){
        this.content = content;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postId = postId;
        this.author = author;
    }
}
