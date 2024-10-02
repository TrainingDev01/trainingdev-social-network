package com.trainingdev.td_bs_management_post.controller;


import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.dto.output.PostDetailList;
import com.trainingdev.td_bs_management_post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDetail createPost(@RequestBody @Valid PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PostDetail updatePost(@RequestBody @Valid PostModified postModified) {
        return postService.updatePost(postModified);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetail deletePost(@PathVariable Integer postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailList getPostByUserId(@PathVariable Integer userId) {
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/user/{userId}/friend")
    @ResponseStatus(HttpStatus.OK)
    public PostDetailList getFriendsPostByUserId(@PathVariable Integer userId) {
        return postService.getFriendsPostsByUserId(userId);
    }
}
