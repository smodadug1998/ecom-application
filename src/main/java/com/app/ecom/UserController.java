package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> fetchAllUsers= userService.fetchAllUsers();
        if(fetchAllUsers.size() == 0 ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(fetchAllUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.fetchUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        boolean updated =  userService.updateUser(id,updatedUser);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public String createUser(@RequestBody User user) {
        userService.addUser(user);
        return "user Added Successfully";
    }

}
