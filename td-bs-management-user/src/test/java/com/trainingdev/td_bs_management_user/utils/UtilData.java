package com.trainingdev.td_bs_management_user.utils;

import com.trainingdev.td_bs_management_user.dto.business.PostDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.entities.UserEntity;
import com.trainingdev.td_bs_management_user.enums.GenderEnum;

import java.time.LocalDate;
import java.util.List;

public class UtilData {

    public static UserRequest createUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName("Santiago");
        userRequest.setLastName("Lozano");
        userRequest.setBirthday(LocalDate.now());
        userRequest.setEmail("santtiagolozano@gmail.com");
        userRequest.setPassword("Al90@asd");
        userRequest.setGender(GenderEnum.MALE);
        return userRequest;
    }

    public static UserDetail createUserDetail() {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1);
        userDetail.setName("Santiago Lozano");
        userDetail.setBirthday(LocalDate.now());
        userDetail.setEmail("santtiagolozano@gmail.com");
        userDetail.setPassword("Al90@asd");
        userDetail.setGender(GenderEnum.MALE);
        return userDetail;
    }

    public static UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setName("Santiago Lozano");
        userEntity.setBirthday(LocalDate.now());
        userEntity.setEmail("santtiagolozano@gmail.com");
        userEntity.setPassword("Al90@asd");
        userEntity.setGender(GenderEnum.MALE);
        return userEntity;
    }

    public static UserProfile createUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserDetail(createUserDetail());
        userProfile.setPostList(List.of(new PostDetail()));
        return userProfile;
    }
}
