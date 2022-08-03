package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDto {
    private String userId;
    private String content;
    private String postId;
}
