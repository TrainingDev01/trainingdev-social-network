package com.trainingdev.td_bs_management_user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.exception.UserNotFoundException;
import com.trainingdev.td_bs_management_user.service.UserService;
import com.trainingdev.td_bs_management_user.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Value("${properties.messages.error.user-does-not-exist}")
    private String userDoesNotExistError;


    @Test
    public void createUser_when_return_201() throws Exception {
        UserRequest userRequest = UtilData.createUserRequest();
        UserDetail userDetail = UtilData.createUserDetail();

        when(userService.createUser(any(UserRequest.class))).thenReturn(userDetail);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(userRequest.getFirstName() + " " + userRequest.getLastName()))
                .andExpect(jsonPath("$.email").value(userRequest.getEmail()))
                .andExpect(jsonPath("$.password").value(userRequest.getPassword()));
    }


    @Test
    public void createUser_when_return_400() throws Exception {
        UserRequest userRequest = new UserRequest();

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void updateUser_when_return_200() throws Exception {
        UserDetail userDetail = UtilData.createUserDetail();

        when(userService.updateUser(any(UserDetail.class))).thenReturn(userDetail);

        mockMvc.perform(put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDetail)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(userDetail.getName()))
                .andExpect(jsonPath("$.email").value(userDetail.getEmail()))
                .andExpect(jsonPath("$.password").value(userDetail.getPassword()));
    }


    @Test
    public void updateUser_when_return_400() throws Exception {
        UserDetail userDetail = new UserDetail();

        mockMvc.perform(put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDetail)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUser_when_return_404() throws Exception {
        UserDetail userDetail = UtilData.createUserDetail();

        when(userService.updateUser(any(UserDetail.class)))
                .thenThrow(new UserNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()),
                        userDoesNotExistError));

        mockMvc.perform(put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDetail)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(String.valueOf(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$.message").value(userDoesNotExistError));
    }


    @Test
    public void getUserById_when_return_200() throws Exception {
        UserProfile userProfile = UtilData.createUserProfile();
        when(userService.getUserById(any(Integer.class))).thenReturn(userProfile);
        mockMvc.perform(get("/api/v1/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userDetail.name").value(userProfile.getUserDetail().getName()))
                .andExpect(jsonPath("$.userDetail.email").value(userProfile.getUserDetail().getEmail()))
                .andExpect(jsonPath("$.userDetail.password").value(userProfile.getUserDetail().getPassword()));
    }
}