package com.app.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<User> fetchAllUsers() {
        return userList;
    }

    @Override
    public List<User> addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
        return userList;
    }

    @Override
    public User fetchUser(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boolean updateUser(Long id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser ->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);

    }
}
