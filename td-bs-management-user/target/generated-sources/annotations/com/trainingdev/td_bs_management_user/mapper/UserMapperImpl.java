package com.trainingdev.td_bs_management_user.mapper;

import com.trainingdev.td_bs_management_user.dto.business.Comment;
import com.trainingdev.td_bs_management_user.dto.business.PostDetail;
import com.trainingdev.td_bs_management_user.dto.business.UserComment;
import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.entities.CommentEntity;
import com.trainingdev.td_bs_management_user.entities.PostEntity;
import com.trainingdev.td_bs_management_user.entities.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T19:27:01-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userRequestToUserEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userRequest.getId() );
        userEntity.birthday( userRequest.getBirthday() );
        userEntity.email( userRequest.getEmail() );
        userEntity.password( userRequest.getPassword() );
        userEntity.gender( userRequest.getGender() );

        userEntity.name( userRequest.getFirstName() + " " + userRequest.getLastName() );

        return userEntity.build();
    }

    @Override
    public UserDetail userEntityToUserDetail(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDetail userDetail = new UserDetail();

        userDetail.setId( userEntity.getId() );
        userDetail.setName( userEntity.getName() );
        userDetail.setBirthday( userEntity.getBirthday() );
        userDetail.setEmail( userEntity.getEmail() );
        userDetail.setPassword( userEntity.getPassword() );
        userDetail.setGender( userEntity.getGender() );
        userDetail.setProfilePhoto( userEntity.getProfilePhoto() );
        userDetail.setCoverPhoto( userEntity.getCoverPhoto() );
        userDetail.setCreationDate( userEntity.getCreationDate() );
        userDetail.setModificationDate( userEntity.getModificationDate() );

        return userDetail;
    }

    @Override
    public UserEntity userDetailToUserEntity(UserDetail userDetail) {
        if ( userDetail == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userDetail.getId() );
        userEntity.name( userDetail.getName() );
        userEntity.birthday( userDetail.getBirthday() );
        userEntity.email( userDetail.getEmail() );
        userEntity.password( userDetail.getPassword() );
        userEntity.gender( userDetail.getGender() );
        userEntity.profilePhoto( userDetail.getProfilePhoto() );
        userEntity.coverPhoto( userDetail.getCoverPhoto() );

        return userEntity.build();
    }

    @Override
    public UserProfile userEntityToUserProfile(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserProfile userProfile = new UserProfile();

        userProfile.setUserDetail( userEntityToUserDetail1( userEntity ) );
        userProfile.setPostList( postEntityListToPostDetailList( userEntity.getPosts() ) );

        return userProfile;
    }

    @Override
    public PostDetail postEntityToPostDetail(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }

        PostDetail postDetail = new PostDetail();

        postDetail.setUserId( postEntityUserId( postEntity ) );
        postDetail.setCommentList( commentEntityListToCommentList( postEntity.getComments() ) );
        if ( postEntity.getId() != null ) {
            postDetail.setId( postEntity.getId().intValue() );
        }
        if ( postEntity.getType() != null ) {
            postDetail.setType( postEntity.getType().name() );
        }
        if ( postEntity.getFeeling() != null ) {
            postDetail.setFeeling( postEntity.getFeeling().name() );
        }
        postDetail.setDescription( postEntity.getDescription() );
        postDetail.setImage( postEntity.getImage() );
        postDetail.setCreationDate( postEntity.getCreationDate() );
        postDetail.setModificationDate( postEntity.getModificationDate() );

        return postDetail;
    }

    @Override
    public Comment commentEntityToComment(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setUser( userEntityToUserComment( commentEntity.getUser() ) );
        if ( commentEntity.getId() != null ) {
            comment.setId( String.valueOf( commentEntity.getId() ) );
        }
        comment.setDescription( commentEntity.getDescription() );
        comment.setCreationDate( commentEntity.getCreationDate() );

        return comment;
    }

    protected UserDetail userEntityToUserDetail1(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDetail userDetail = new UserDetail();

        userDetail.setId( userEntity.getId() );
        userDetail.setName( userEntity.getName() );
        userDetail.setBirthday( userEntity.getBirthday() );
        userDetail.setEmail( userEntity.getEmail() );
        userDetail.setPassword( userEntity.getPassword() );
        userDetail.setGender( userEntity.getGender() );
        userDetail.setProfilePhoto( userEntity.getProfilePhoto() );
        userDetail.setCoverPhoto( userEntity.getCoverPhoto() );
        userDetail.setCreationDate( userEntity.getCreationDate() );
        userDetail.setModificationDate( userEntity.getModificationDate() );

        return userDetail;
    }

    protected List<PostDetail> postEntityListToPostDetailList(List<PostEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PostDetail> list1 = new ArrayList<PostDetail>( list.size() );
        for ( PostEntity postEntity : list ) {
            list1.add( postEntityToPostDetail( postEntity ) );
        }

        return list1;
    }

    private Integer postEntityUserId(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }
        UserEntity user = postEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<Comment> commentEntityListToCommentList(List<CommentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Comment> list1 = new ArrayList<Comment>( list.size() );
        for ( CommentEntity commentEntity : list ) {
            list1.add( commentEntityToComment( commentEntity ) );
        }

        return list1;
    }

    protected UserComment userEntityToUserComment(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserComment userComment = new UserComment();

        if ( userEntity.getId() != null ) {
            userComment.setId( String.valueOf( userEntity.getId() ) );
        }
        userComment.setName( userEntity.getName() );
        userComment.setProfilePhoto( userEntity.getProfilePhoto() );

        return userComment;
    }
}
