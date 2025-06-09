package com.app.ecom.response;

import com.app.ecom.dto.AddressDTO;
import com.app.ecom.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO addressDTO;

}
