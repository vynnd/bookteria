package com.devteria.profile.dto.response;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
