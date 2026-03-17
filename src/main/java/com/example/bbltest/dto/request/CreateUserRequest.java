package com.example.bbltest.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateUserRequest {

    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @Email
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9][0-9\\-]{8,14}[0-9]$",
            message = "Invalid phone number format"
    )
    private String phone;

    @URL(message = "Invalid URL format")
    private String website;
}
