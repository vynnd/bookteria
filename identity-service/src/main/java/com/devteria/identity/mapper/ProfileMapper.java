package com.devteria.identity.mapper;

import com.devteria.identity.dto.request.UserCreationRequest;
import com.devteria.identity.dto.request.profile_service.ProfileCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest user);
}
