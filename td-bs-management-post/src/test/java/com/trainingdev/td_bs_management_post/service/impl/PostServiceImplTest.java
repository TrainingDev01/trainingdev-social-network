package com.trainingdev.td_bs_management_post.service.impl;

import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.dto.output.PostDetailList;
import com.trainingdev.td_bs_management_post.entities.PostEntity;
import com.trainingdev.td_bs_management_post.entities.UserEntity;
import com.trainingdev.td_bs_management_post.exception.NotFoundException;
import com.trainingdev.td_bs_management_post.mapper.PostMapper;
import com.trainingdev.td_bs_management_post.repository.PostRepository;
import com.trainingdev.td_bs_management_post.repository.RelationshipRepository;
import com.trainingdev.td_bs_management_post.repository.UserRepository;
import com.trainingdev.td_bs_management_post.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringBootTest
class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RelationshipRepository relationshipRepository;

    @Value("${properties.messages.error.post-not-found}")
    private String postNotFoundError;

    @Value("${properties.messages.error.no-posts-found}")
    private String noPostsFound;

    @Value("${properties.messages.error.user-not-found}")
    private String userNotFoundError;



    @Test
    void createPost_when_return_successful() {
        PostRequest postRequest = UtilData.createPostRequest();
        UserEntity userEntity = UtilData.createUserEntity();
        PostEntity postEntity = UtilData.createPostEntity();
        PostDetail postDetail = UtilData.createPostDetail();

        when(userRepository.findById(postRequest.getUserId())).thenReturn(Optional.of(userEntity));
        when(postMapper.postRequestToPostEntity(postRequest, userEntity)).thenReturn(postEntity);
        when(postRepository.save(postEntity)).thenReturn(postEntity);
        when(postMapper.postEntityToPostDetail(postEntity)).thenReturn(postDetail);

        PostDetail result = postService.createPost(postRequest);

        assertEquals(postDetail, result);
    }

    @Test
    void createPost_when_return_notFoundException() {
        PostRequest postRequest = UtilData.createPostRequest();

        when(userRepository.findById(postRequest.getUserId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.createPost(postRequest);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

    @Test
    void updatePost_whent_returnSuccessful() {
        PostModified postModified = UtilData.createPostModified();
        PostEntity postEntity = UtilData.createPostEntity();
        PostDetail postDetail = UtilData.createPostDetail();

        when(postRepository.findById(postModified.getId())).thenReturn(Optional.of(postEntity));
        when(userRepository.existsById(postModified.getUserId())).thenReturn(true);
        doNothing().when(postMapper).updatePostEntityFromModified(postModified, postEntity);
        when(postRepository.save(postEntity)).thenReturn(postEntity);
        when(postMapper.postEntityToPostDetail(postEntity)).thenReturn(postDetail);

        PostDetail result = postService.updatePost(postModified);

        assertEquals(postDetail, result);
    }

    @Test
    void updatePost_when_returnNotFoundException() {
        PostModified postModified = UtilData.createPostModified();

        when(postRepository.findById(postModified.getId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.updatePost(postModified);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

    @Test
    void updatePost_when_returnFoundException() {
        PostModified postModified = UtilData.createPostModified();
        PostEntity postEntity = UtilData.createPostEntity();

        when(postRepository.findById(postModified.getId())).thenReturn(Optional.of(postEntity));
        when(userRepository.existsById(postModified.getUserId())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.updatePost(postModified);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

    @Test
    void deletePost_when_returnSuccessful() {
        PostEntity postEntity = UtilData.createPostEntity();
        PostDetail postDetail = UtilData.createPostDetail();

        when(postRepository.findById(100)).thenReturn(Optional.of(postEntity));
        when(postMapper.postEntityToPostDetail(postEntity)).thenReturn(postDetail);

        PostDetail result = postService.deletePost(100);

        verify(postRepository).delete(postEntity);
        assertEquals(postDetail, result);
    }

    @Test
    void deletePost_when_returnNotFoundException() {
        when(postRepository.findById(100)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.deletePost(100);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

    @Test
    void getPostsByUserId_when_returnSuccessful() {
        List<PostEntity> posts = List.of(UtilData.createPostEntity());
        when(userRepository.existsById(100)).thenReturn(true);
        when(postRepository.findAllByUserId(100)).thenReturn(posts);
        when(postMapper.postEntitiesToPostDetailList(posts)).thenReturn(List.of(UtilData.createPostDetail()));

        PostDetailList result = postService.getPostsByUserId(100);

        assertNotNull(result);
        assertEquals(1, result.getPostList().size());
    }

    @Test
    void getPostsByUserId_when_returnNotFoundException() {
        when(userRepository.existsById(100)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.getPostsByUserId(100);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }

    @Test
    void getFriendsPostsByUserId_when_returnSuccessful() {
        List<Integer> friendList = List.of(101, 102);
        List<PostEntity> friendPosts = List.of(UtilData.createPostEntity());
        List<PostEntity> ownPosts = List.of(UtilData.createPostEntity());

        when(userRepository.existsById(100)).thenReturn(true);
        when(relationshipRepository.findFriendIdsByUserId(100)).thenReturn(friendList);
        when(postRepository.findAllByUserIds(friendList)).thenReturn(friendPosts);
        when(postRepository.findAllByUserId(100)).thenReturn(ownPosts);
        when(postMapper.postEntitiesToPostDetailList(anyList())).thenReturn(List.of(UtilData.createPostDetail()));

        PostDetailList result = postService.getFriendsPostsByUserId(100);

        assertNotNull(result);
        assertEquals(1, result.getPostList().size());
    }

    @Test
    void getFriendsPostsByUserId_when_returnNotFoundException() {
        when(userRepository.existsById(100)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            postService.getFriendsPostsByUserId(100);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }
}