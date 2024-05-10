package com.houlala.marketplace.location;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    private String name;
    private long id;
    private String imageUrl;
    private String email;
    private String uniqueIdentifier;
}
