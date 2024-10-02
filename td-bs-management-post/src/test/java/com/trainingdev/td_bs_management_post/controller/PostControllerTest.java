package com.trainingdev.td_bs_management_post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainingdev.td_bs_management_post.dto.input.PostModified;
import com.trainingdev.td_bs_management_post.dto.input.PostRequest;
import com.trainingdev.td_bs_management_post.dto.output.PostDetail;
import com.trainingdev.td_bs_management_post.dto.output.PostDetailList;
import com.trainingdev.td_bs_management_post.exception.NotFoundException;
import com.trainingdev.td_bs_management_post.service.PostService;
import com.trainingdev.td_bs_management_post.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Value("${properties.messages.error.post-not-found}")
    private String postNotFoundError;

    @Value("${properties.messages.error.post-description-cannot-be-empty}")
    private String postDescriptionError;

    @Value("${properties.messages.error.post-type-cannot-be-null}")
    private String postTypeError;

    @Test
    public void createPost_when_return201() throws Exception {
        PostRequest postRequest = UtilData.createPostRequest();
        PostDetail postDetail = UtilData.createPostDetail();

        when(postService.createPost(any(PostRequest.class))).thenReturn(postDetail);

        mockMvc.perform(post("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.type").value("PUBLIC"))
                .andExpect(jsonPath("$.feeling").value("HAPPY"))
                .andExpect(jsonPath("$.description").value("This is an example post detail."))
                .andExpect(jsonPath("$.image").value("https://example.com/image.jpg"));
    }

    @Test
    public void createPost_when_return400() throws Exception {
        PostRequest postRequest = UtilData.createPostRequest();
        postRequest.setType(null);

        mockMvc.perform(post("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void updatePost_when_return200() throws Exception {
        PostModified postModified = UtilData.createPostModified();
        PostDetail postDetail = UtilData.createPostDetail();

        when(postService.updatePost(any(PostModified.class))).thenReturn(postDetail);

        mockMvc.perform(put("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postModified)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.type").value("PUBLIC"))
                .andExpect(jsonPath("$.feeling").value("HAPPY"))
                .andExpect(jsonPath("$.description").value("This is an example post detail."))
                .andExpect(jsonPath("$.image").value("https://example.com/image.jpg"));
    }

    @Test
    public void updatePost_when_returns400() throws Exception {
        PostModified postModified = UtilData.createPostModified();
        postModified.setDescription("");

        mockMvc.perform(put("/api/v1/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postModified)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void deletePost_when_return200() throws Exception {
        PostDetail postDetail = UtilData.createPostDetail();

        when(postService.deletePost(1)).thenReturn(postDetail);

        mockMvc.perform(delete("/api/v1/post/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.description").value("This is an example post detail."))
                .andExpect(jsonPath("$.user.id").value(100));
    }

    @Test
    public void deletePost_when_returns404() throws Exception {
        when(postService.deletePost(100)).thenThrow(new NotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), postNotFoundError));

        mockMvc.perform(delete("/api/v1/post/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(postNotFoundError));
    }

    @Test
    public void getPostsByUserId_when_return200() throws Exception {
        PostDetailList postDetailList = new PostDetailList();
        postDetailList.setPostList(List.of(UtilData.createPostDetail()));

        when(postService.getPostsByUserId(1)).thenReturn(postDetailList);

        mockMvc.perform(get("/api/v1/post/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postList[0].id").value(100))
                .andExpect(jsonPath("$.postList[0].description").value("This is an example post detail."))
                .andExpect(jsonPath("$.postList[0].user.id").value(100));
    }

    @Test
    public void getFriendsPostsByUserId_when_return200() throws Exception {
        PostDetailList postDetailList = new PostDetailList();
        postDetailList.setPostList(List.of(UtilData.createPostDetail()));

        when(postService.getFriendsPostsByUserId(1)).thenReturn(postDetailList);

        mockMvc.perform(get("/api/v1/post/user/1/friend"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postList[0].id").value(100))
                .andExpect(jsonPath("$.postList[0].description").value("This is an example post detail."))
                .andExpect(jsonPath("$.postList[0].user.id").value(100));
    }

}