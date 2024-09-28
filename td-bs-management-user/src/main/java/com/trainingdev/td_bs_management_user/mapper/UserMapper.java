package com.trainingdev.td_bs_management_user.mapper;

import com.trainingdev.td_bs_management_user.dto.business.Comment;
import com.trainingdev.td_bs_management_user.dto.business.PostDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.entities.CommentEntity;
import com.trainingdev.td_bs_management_user.entities.PostEntity;
import com.trainingdev.td_bs_management_user.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", expression = "java(userRequest.getFirstName() + \" \" + userRequest.getLastName())")
    UserEntity userRequestToUserEntity(UserRequest userRequest);

    UserDetail userEntityToUserDetail(UserEntity userEntity);

    UserEntity userDetailToUserEntity(UserDetail userDetail);

    @Mapping(source = "posts", target = "postList")
    @Mapping(source = "id", target = "userDetail.id")
    @Mapping(source = "name", target = "userDetail.name")
    @Mapping(source = "birthday", target = "userDetail.birthday")
    @Mapping(source = "email", target = "userDetail.email")
    @Mapping(source = "password", target = "userDetail.password")
    @Mapping(source = "gender", target = "userDetail.gender")
    @Mapping(source = "profilePhoto", target = "userDetail.profilePhoto")
    @Mapping(source = "coverPhoto", target = "userDetail.coverPhoto")
    @Mapping(source = "creationDate", target = "userDetail.creationDate")
    @Mapping(source = "modificationDate", target = "userDetail.modificationDate")
    UserProfile userEntityToUserProfile(UserEntity userEntity);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comments", target = "commentList")
    PostDetail postEntityToPostDetail(PostEntity postEntity);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "user.name", target = "user.name")
    @Mapping(source = "user.profilePhoto", target = "user.profilePhoto")
    Comment commentEntityToComment(CommentEntity commentEntity);

}
