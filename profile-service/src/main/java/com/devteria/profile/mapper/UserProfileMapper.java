package com.devteria.profile.mapper;

import org.mapstruct.Mapper;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileReponse;
import com.devteria.profile.entity.UserProfile;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileReponse toUserProfileReponse(UserProfile entity);

    void updateUserProfile(@MappingTarget  UserProfile entity, ProfileCreationRequest request);

    List<UserProfileReponse> toListUserProfile(List<UserProfile> userProfiles);
}
