package com.trainingdev.td_bs_management_user.service.impl;

import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.entities.UserEntity;
import com.trainingdev.td_bs_management_user.exception.UserNotFoundException;
import com.trainingdev.td_bs_management_user.mapper.UserMapper;
import com.trainingdev.td_bs_management_user.repository.UserRepository;
import com.trainingdev.td_bs_management_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Value("${properties.messages.error.user-does-not-exist}")
    private String userDoesntExistError;


    @Override
    public UserDetail createUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.userRequestToUserEntity(userRequest);
        UserEntity userEntitySaved = userRepository.save(userEntity);
        return userMapper.userEntityToUserDetail(userEntitySaved);
    }

    @Override
    public UserDetail updateUser(UserDetail userDetail) {
        userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userDoesntExistError));
        UserEntity userEntityUpdated = userRepository.save(userMapper.userDetailToUserEntity(userDetail));
        return userMapper.userEntityToUserDetail(userEntityUpdated);
    }

    @Override
    public UserProfile getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(HttpStatus.NOT_FOUND.value()), userDoesntExistError));
        return userMapper.userEntityToUserProfile(userEntity);
    }
}
