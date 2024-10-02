package com.trainingdev.td_bs_management_post.service;

import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.dto.output.PostDetailList;

public interface PostService {

    PostDetail createPost(PostRequest postRequest);

    PostDetail updatePost(PostModified postModified);

    PostDetail deletePost(Integer postId);

    PostDetailList getPostsByUserId(Integer userId);

    PostDetailList getFriendsPostsByUserId(Integer userId);
}
