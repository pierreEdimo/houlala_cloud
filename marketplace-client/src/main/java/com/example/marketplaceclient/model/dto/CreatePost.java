package com.example.marketplaceclient.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePost {
    String content;
    String locationId;
    String imageUrl;
}
