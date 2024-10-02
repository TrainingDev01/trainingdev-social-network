package com.trainingdev.td_bs_management_user.service;

import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserModified;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;

public interface UserService {

    UserDetail createUser(UserRequest userRequest);

    UserDetail updateUser(UserModified userModified);

    UserProfile getUserById(Integer id);
}
