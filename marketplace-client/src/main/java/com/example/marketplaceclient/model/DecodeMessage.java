package com.example.marketplaceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DecodeMessage {
    private int statusCode;
    private String error;
    private String message;
}
