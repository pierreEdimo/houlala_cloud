package com.example.marketplaceclient.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String city;
    private String country;
    private String streetName;
    private String poBox;
    private String userName;
    private String houseNumber;
}
