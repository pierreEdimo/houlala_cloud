package com.example.discoveryorchestrator.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleLocation {
    private long id;
    private String name;
    private String email;
    private String telephoneNumber;
}
