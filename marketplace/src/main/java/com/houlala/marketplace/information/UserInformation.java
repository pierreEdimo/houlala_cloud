package com.houlala.marketplace.information;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInformation {
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String city;
    private String country;
    private String poBox;
    private String streetName;
    private String houseNumber;
    private String userName;
}
