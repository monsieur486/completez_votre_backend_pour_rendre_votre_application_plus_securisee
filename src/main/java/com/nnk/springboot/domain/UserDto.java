package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type User dto.
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String fullname;
    private String password;
    private String role;
}
