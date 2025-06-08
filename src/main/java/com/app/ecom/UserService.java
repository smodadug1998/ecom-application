package com.app.ecom;

import java.util.List;

public interface UserService{
    public List<User> fetchAllUsers();
    public List<User> addUser(User user);

    User fetchUser(Long id);

    Boolean updateUser(Long id, User updatedUser);
}
