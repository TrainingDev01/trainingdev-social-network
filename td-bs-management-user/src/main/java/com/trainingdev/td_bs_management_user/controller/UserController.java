package com.trainingdev.td_bs_management_user.controller;

import com.trainingdev.td_bs_management_user.dto.input.UserDetail;
import com.trainingdev.td_bs_management_user.dto.input.UserRequest;
import com.trainingdev.td_bs_management_user.dto.output.UserProfile;
import com.trainingdev.td_bs_management_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetail createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public UserDetail updateUser(@RequestBody @Valid UserDetail userDetail) {
        return userService.updateUser(userDetail);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfile getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
