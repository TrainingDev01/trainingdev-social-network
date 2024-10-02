package com.trainingdev.td_bs_management_post.utils;

import com.trainingdev.td_bs_management_post.dto.business.User;
import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.entities.PostEntity;
import com.trainingdev.td_bs_management_post.entities.UserEntity;
import com.trainingdev.td_bs_management_post.enums.GenderEnum;
import com.trainingdev.td_bs_management_post.enums.PostFeeling;
import com.trainingdev.td_bs_management_post.enums.PostType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilData {

    public static PostRequest createPostRequest() {
        PostRequest postRequest = new PostRequest();
        postRequest.setUserId(100);
        postRequest.setType(PostType.PUBLIC);
        postRequest.setFeeling(PostFeeling.HAPPY);
        postRequest.setDescription("This is an example post.");
        postRequest.setImage("https://example.com/image.jpg");
        return postRequest;
    }

    public static PostDetail createPostDetail() {
        PostDetail postDetail = new PostDetail();
        postDetail.setId(100);
        postDetail.setUser(createUser());
        postDetail.setType(PostType.PUBLIC);
        postDetail.setFeeling(PostFeeling.HAPPY);
        postDetail.setDescription("This is an example post detail.");
        postDetail.setImage("https://example.com/image.jpg");
        postDetail.setCreationDate(LocalDate.now());
        postDetail.setModificationDate(LocalDate.now());
        return postDetail;
    }

    public static PostModified createPostModified() {
        PostModified postModified = new PostModified();
        postModified.setId(100);
        postModified.setUserId(100);
        postModified.setType(PostType.PUBLIC);
        postModified.setFeeling(PostFeeling.HAPPY);
        postModified.setDescription("This is an updated post description.");
        postModified.setImage("https://example.com/updated-image.jpg");
        return postModified;
    }

    public static User createUser() {
        User user = new User();
        user.setId(100);
        user.setName("Santiago Lozano");
        user.setProfilePhoto("https://example.com/profile.jpg");
        return user;
    }

    public static UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(100);
        userEntity.setName("Santiago Lozano");
        userEntity.setBirthday(LocalDate.of(1995, 5, 10));
        userEntity.setEmail("santiagolozano@gmail.com");
        userEntity.setPassword("Al90@asd");
        userEntity.setGender(GenderEnum.MALE);
        userEntity.setProfilePhoto("https://example.com/profile-photo.jpg");
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setModificationDate(LocalDateTime.now());
        return userEntity;
    }

    public static PostEntity createPostEntity() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(100);
        postEntity.setUser(createUserEntity());
        postEntity.setType(PostType.PUBLIC.toString());
        postEntity.setFeeling(PostFeeling.HAPPY.toString());
        postEntity.setDescription("This is an example post description.");
        postEntity.setImage("https://example.com/post-image.jpg");
        postEntity.setCreationDate(LocalDateTime.now());
        postEntity.setModificationDate(LocalDateTime.now());
        return postEntity;
    }
}
