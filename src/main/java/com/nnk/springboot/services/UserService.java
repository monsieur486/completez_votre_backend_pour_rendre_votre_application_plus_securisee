package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.UserDto;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository  the user repository
     * @param passwordEncoder the password encoder
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Save.
     *
     * @param user the user
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Save user dto.
     *
     * @param userDto the user dto
     */
    public void saveUserDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullname(userDto.getFullname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Delete.
     *
     * @param user the user
     */
    public void delete(User user) {
        userRepository.delete(user);
    }
}
