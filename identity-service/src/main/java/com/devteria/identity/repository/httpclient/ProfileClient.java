package com.devteria.identity.repository.httpclient;

import com.devteria.identity.configuration.AuthenticationRequestInterceptor;
import com.devteria.identity.dto.request.profile_service.ProfileCreationRequest;
import com.devteria.identity.dto.response.profile_service.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {

    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request);

    @PutMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse updateProfile(@RequestBody ProfileCreationRequest request);

    @DeleteMapping(value = "/internal/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteProfile(@PathVariable("userId") String userId);
}
