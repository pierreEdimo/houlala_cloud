package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private String content;
    private String _id;
    private String createdAt;
    private String updatedAt;

    public CommentDto(
            String content,
            String _id,
            String createdAt,
            String updatedAt
    ){
        this.content = content;
        this._id = _id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
