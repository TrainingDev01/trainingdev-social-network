package com.trainingdev.td_bs_management_user.service;

import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.entities.UserEntity;
import com.trainingdev.td_bs_management_user.exception.UserNotFoundException;
import com.trainingdev.td_bs_management_user.mapper.UserMapper;
import com.trainingdev.td_bs_management_user.repository.UserRepository;
import com.trainingdev.td_bs_management_user.service.impl.UserServiceImpl;
import com.trainingdev.td_bs_management_user.utils.UtilData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Value("${properties.messages.error.user-does-not-exist}")
    private String userDoesntExistError;


    @Test
    public void createUser_when_return_success() {
        UserRequest userRequest = UtilData.createUserRequest();
        UserEntity userEntity = UtilData.createUserEntity();
        UserEntity userEntitySaved = UtilData.createUserEntity();
        UserDetail userDetail = UtilData.createUserDetail();

        when(userMapper.userRequestToUserEntity(userRequest)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntitySaved);
        when(userMapper.userEntityToUserDetail(userEntitySaved)).thenReturn(userDetail);

        UserDetail result = userServiceImpl.createUser(userRequest);
        assertEquals(userDetail, result);
    }

    @Test
    public void updateUser_when_return_success() {
        UserDetail userDetail = UtilData.createUserDetail();
        UserEntity userEntity = UtilData.createUserEntity();
        UserEntity userEntityUpdated = UtilData.createUserEntity();
        UserDetail userDetailUpdated = UtilData.createUserDetail();

        when(userRepository.findById(userDetail.getId())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityUpdated);
        when(userMapper.userDetailToUserEntity(userDetail)).thenReturn(userEntity);
        when(userMapper.userEntityToUserDetail(userEntityUpdated)).thenReturn(userDetailUpdated);

        UserDetail result = userServiceImpl.updateUser(userDetail);
        assertEquals(userDetailUpdated, result);
    }


    @Test
    public void updateUserNotFound_when_return_not_found() {
        UserDetail userDetail = UtilData.createUserDetail();

        when(userRepository.findById(userDetail.getId())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.updateUser(userDetail);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }


    @Test
    public void getUserById_when_return_success() {
        UserEntity userEntity = UtilData.createUserEntity();
        UserProfile userProfile = UtilData.createUserProfile();

        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        when(userMapper.userEntityToUserProfile(userEntity)).thenReturn(userProfile);

        UserProfile result = userServiceImpl.getUserById(userEntity.getId());
        assertEquals(userProfile, result);
    }


    @Test
    public void getUserById_when_return_NotFound() {
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.getUserById(userId);
        });

        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), exception.getCode());
    }
}