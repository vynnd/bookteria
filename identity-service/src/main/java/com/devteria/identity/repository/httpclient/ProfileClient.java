package com.devteria.identity.repository.httpclient;

import com.devteria.identity.dto.request.profile_service.ProfileCreationRequest;
import com.devteria.identity.dto.response.profile_service.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}")
public interface ProfileClient {

    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request);
}
