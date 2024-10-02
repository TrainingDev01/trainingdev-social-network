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
import com.trainingdev.td_bs_management_post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final RelationshipRepository relationshipRepository;

    @Value("${properties.messages.error.post-not-found}")
    private String postNotFoundError;

    @Value("${properties.messages.error.no-posts-found}")
    private String noPostsFound;

    @Value("${properties.messages.error.user-not-found}")
    private String userNotFoundError;


    @Override
    public PostDetail createPost(PostRequest postRequest) {
        Optional<UserEntity> userEntity = userRepository.findById(postRequest.getUserId());
        if (userEntity.isEmpty()) {
            throw new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userNotFoundError);
        }
        PostEntity postEntity = postMapper.postRequestToPostEntity(postRequest, userEntity.get());
        PostEntity savedPost = postRepository.save(postEntity);
        return postMapper.postEntityToPostDetail(savedPost);
    }

    @Override
    public PostDetail updatePost(PostModified postModified) {
        PostEntity postEntity = postRepository.findById(postModified.getId())
                .orElseThrow(() -> new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), postNotFoundError));

        if (!userRepository.existsById(postModified.getUserId())) {
            throw new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userNotFoundError);
        }

        postMapper.updatePostEntityFromModified(postModified, postEntity);
        PostEntity updatedPost = postRepository.save(postEntity);
        return postMapper.postEntityToPostDetail(updatedPost);
    }

    @Override
    public PostDetail deletePost(Integer postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), postNotFoundError));

        postRepository.delete(postEntity);
        return postMapper.postEntityToPostDetail(postEntity);
    }

    @Override
    public PostDetailList getPostsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userNotFoundError);
        }

        List<PostEntity> posts = postRepository.findAllByUserId(userId);
        return new PostDetailList(postMapper.postEntitiesToPostDetailList(posts));
    }

    @Override
    public PostDetailList getFriendsPostsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userNotFoundError);
        }
        List<Integer> firendList = relationshipRepository.findFriendIdsByUserId(userId);
        List<PostEntity> friendsPosts = postRepository.findAllByUserIds(firendList);
        List<PostEntity> ownPosts = postRepository.findAllByUserId(userId);
        List<PostEntity> combinedPosts = Stream.concat(friendsPosts.stream(), ownPosts.stream()).collect(Collectors.toList());
        if (combinedPosts.isEmpty()) {
            throw new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), noPostsFound);
        }
        return new PostDetailList(postMapper.postEntitiesToPostDetailList(combinedPosts));
    }
}
