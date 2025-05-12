package com.devteria.profile.controller;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileReponse;
import com.devteria.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    UserProfileReponse createProfile(@RequestBody ProfileCreationRequest request) {
        return userProfileService.createProfile(request);
    }

    @PutMapping("/internal/users")
    UserProfileReponse updateProfileByUserId(@RequestBody ProfileCreationRequest request){
        return userProfileService.updateProfileByUserId(request);
    }

    @DeleteMapping("/internal/users/{userId}")
    void deleteUserProfile(@PathVariable String userId){
        userProfileService.deleteUserProfile(userId);
    }
}
