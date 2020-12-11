package com.iths.airtravels.service;

import com.iths.airtravels.entity.User;
import com.iths.airtravels.repository.AuthGroupRepository;
import com.iths.airtravels.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.iths.airtravels.entity.AuthGroup;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    AuthGroupRepository authGroupRepository;

    final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
     //   authGroupRepository.save(new AuthGroup(user.getUsername(), "USER"));
     //   authGroupRepository.save(new AuthGroup(user.getUsername(), "ADMIN"));
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        userRepository.deleteById(foundUser.get().getId());
    }
}
