package com.example.discoveryorchestrator.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Country {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String code;
}
