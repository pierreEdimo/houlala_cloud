package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDto {
    private String user;
    private String content;
    private String postId;
}
