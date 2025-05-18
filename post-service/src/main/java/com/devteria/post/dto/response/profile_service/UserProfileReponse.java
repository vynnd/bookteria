package com.devteria.post.dto.response.profile_service;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileReponse {
    String id;
    String userId;
    String firstName;
    String lastName;
    String userName;
    LocalDate dob;
    String city;
}
