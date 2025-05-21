package com.devteria.profile.controller;

import com.devteria.profile.dto.ApiResponse;
import com.devteria.profile.dto.request.UpdateProfileRequest;
import org.springframework.web.bind.annotation.*;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileReponse;
import com.devteria.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/users/{profileId}")
    UserProfileReponse getProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @GetMapping("/users/my-profile")
    ApiResponse<UserProfileReponse> myInfo() {
        return ApiResponse.<UserProfileReponse>builder()
                .result(userProfileService.myInfo())
                .build();
    }

   @GetMapping("/users")
    List<UserProfileReponse> getUsers(){
        return userProfileService.allUsers();
   }

    @PutMapping("/users/{profileId}")
    UserProfileReponse updateProfile(@PathVariable String profileId, @RequestBody ProfileCreationRequest request){
        return userProfileService.updateProfile(profileId, request);
    }

    @PutMapping("/users/my-profile")
    ApiResponse<UserProfileReponse> updateMyInfo(@RequestBody UpdateProfileRequest request) {
        return ApiResponse.<UserProfileReponse>builder()
                .result(userProfileService.updateMyInfo(request))
                .build();
    }
}
