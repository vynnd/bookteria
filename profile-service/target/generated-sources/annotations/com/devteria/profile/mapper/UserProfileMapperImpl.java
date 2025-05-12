package com.devteria.profile.mapper;

import com.devteria.profile.dto.request.ProfileCreationRequest;
import com.devteria.profile.dto.response.UserProfileReponse;
import com.devteria.profile.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserProfileMapperImpl implements UserProfileMapper {

    @Override
    public UserProfile toUserProfile(ProfileCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.firstName( request.getFirstName() );
        userProfile.lastName( request.getLastName() );
        userProfile.dob( request.getDob() );
        userProfile.city( request.getCity() );

        return userProfile.build();
    }

    @Override
    public UserProfileReponse toUserProfileReponse(UserProfile entity) {
        if ( entity == null ) {
            return null;
        }

        UserProfileReponse.UserProfileReponseBuilder userProfileReponse = UserProfileReponse.builder();

        userProfileReponse.id( entity.getId() );
        userProfileReponse.firstName( entity.getFirstName() );
        userProfileReponse.lastName( entity.getLastName() );
        userProfileReponse.dob( entity.getDob() );
        userProfileReponse.city( entity.getCity() );

        return userProfileReponse.build();
    }

    @Override
    public void updateUserProfile(UserProfile entity, ProfileCreationRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setFirstName( request.getFirstName() );
        entity.setLastName( request.getLastName() );
        entity.setDob( request.getDob() );
        entity.setCity( request.getCity() );
    }

    @Override
    public List<UserProfileReponse> toListUserProfile(List<UserProfile> userProfiles) {
        if ( userProfiles == null ) {
            return null;
        }

        List<UserProfileReponse> list = new ArrayList<UserProfileReponse>( userProfiles.size() );
        for ( UserProfile userProfile : userProfiles ) {
            list.add( toUserProfileReponse( userProfile ) );
        }

        return list;
    }
}
