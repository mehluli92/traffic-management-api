package com.traffic.tmanagement.service.user;

import com.traffic.tmanagement.entity.User;
import com.traffic.tmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            userDetails.setEc_number(userDetails.getEc_number());
            user.setPassword(userDetails.getPassword());
            user.setEnabled(userDetails.getEnabled());
            user.setRoles(userDetails.getRoles());
            user.setPermissions(userDetails.getPermissions());
            return userRepository.save(user);
        } else {
            userDetails.setId(id);
            return userRepository.save(userDetails);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
