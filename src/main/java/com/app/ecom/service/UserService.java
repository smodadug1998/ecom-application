package com.app.ecom.service;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> fetchAllUsers();

    void addUser(UserRequest UserRequest);

    Optional<UserResponse> fetchUser(Long id);

    Boolean updateUser(Long id, UserRequest updatedUserRequest);
}
