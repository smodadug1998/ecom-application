package com.app.ecom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers() {
        return userList;
    }

    public List<User> addUser(User user) {
        userList.add(user);
        return userList;
    }
}
