package com.devteria.post.repository.httpclient;

import com.devteria.post.configuration.AuthenticationRequestInterceptor;
import com.devteria.post.dto.ApiResponse;
import com.devteria.post.dto.response.profile_service.UserProfileReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {
    @GetMapping(value = "/internal/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileReponse> getByUserId(@PathVariable String userId);
}
