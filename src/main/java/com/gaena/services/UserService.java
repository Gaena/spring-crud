package com.gaena.services;

import com.gaena.domain.User;
import com.gaena.exceptions.UserNotFoundException;
import com.gaena.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser) {
        if (Objects.isNull(updatedUser))
            throw new RuntimeException("ID can not be null");

        User oldUser = userRepository
                .findById(updatedUser.getId())
                .orElseThrow(UserNotFoundException::new);

        BeanUtils.copyProperties(updatedUser, oldUser);
        return userRepository.save(oldUser);
    }

    public void deleteUser(Long id) {

        User oldUser = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(oldUser);
    }


    public User getUserByName(String name) {
        return userRepository
                .findUserByName(name)
                .orElseThrow(UserNotFoundException::new);
    }
}
