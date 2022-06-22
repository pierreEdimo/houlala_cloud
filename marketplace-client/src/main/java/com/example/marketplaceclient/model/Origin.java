package com.example.marketplaceclient.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Origin {

    private String label;

    public Origin(){}

    public Origin(String label){
        this.label = label;
    }

}
