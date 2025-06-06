package com.devteria.profile.service;

import com.devteria.profile.dto.request.UpdateProfileRequest;
import com.devteria.profile.exception.AppException;
import com.devteria.profile.exception.ErrorCode;
import com.devteria.profile.repository.httpclient.FileClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileReponse;
import com.devteria.profile.entity.UserProfile;
import com.devteria.profile.mapper.UserProfileMapper;
import com.devteria.profile.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;
    FileClient fileClient;

    public UserProfileReponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    public UserProfileReponse getProfile(String id) {
        UserProfile userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    public UserProfileReponse myInfo() {
        var userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserProfile userProfile =
                userProfileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    public UserProfileReponse updateMyInfo(UpdateProfileRequest request){
        var userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserProfile userProfile =
                userProfileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userProfileMapper.updateUserProfile(userProfile, request);
        return userProfileMapper.toUserProfileReponse(userProfileRepository.save(userProfile));
    }

    public UserProfileReponse updateAvatar(MultipartFile file){
        var userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserProfile userProfile =
                userProfileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var fileResponse = fileClient.uploadMedia(file);

        // upload file - invoke an api file service
        userProfile.setAvatar(fileResponse.getResult().getUrl());
        return userProfileMapper.toUserProfileReponse(userProfileRepository.save(userProfile));
    }

    public UserProfileReponse updateProfile(String id, ProfileCreationRequest request) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userProfileMapper.updateUserProfile(userProfile, request);

        return userProfileMapper.toUserProfileReponse(userProfileRepository.save(userProfile));
    }

    public UserProfileReponse getByUserId(String userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userProfileMapper.toUserProfileReponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileReponse> allUsers(){
       return userProfileMapper.toListUserProfile(userProfileRepository.findAll());
    }

    public UserProfileReponse updateProfileByUserId(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        userProfileMapper.updateUserProfile(userProfile, request);

        return userProfileMapper.toUserProfileReponse(userProfileRepository.save(userProfile));
    }

    public void deleteUserProfile(String userId){
        userProfileRepository.deleteByUserId(userId);
    }
}
