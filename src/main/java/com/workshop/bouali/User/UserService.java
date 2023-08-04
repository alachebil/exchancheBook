package com.workshop.bouali.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserP> getUsers() {
        return userRepository.findAll();
    }


    public Optional<UserP> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    //    public Optional<UserP> findUserByEmail(String email) {
    //        return userRepository.findUserByEmail(email);
    //    }


    public void addNewUser(UserP user) throws IllegalAccessException {

        Optional<UserP> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalAccessException("email is taken");
        }

        userRepository.save(user);

        System.out.println(user);
    }


    public void deleteUser(Long userId) throws IllegalAccessException {


        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalAccessException("user with id " + userId + " is not exist ");
        } else
            userRepository.deleteById(userId);
        System.out.println("the student is deleted successfully");
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) throws IllegalAccessException {

        UserP user = userRepository.findById(userId).orElseThrow(() -> new IllegalAccessException("student with id " + userId + " is not exist "));
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }


        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<UserP> UserOptional = userRepository.findUserByEmail(email);
            if (UserOptional.isPresent()) {
                throw new IllegalAccessException("email is taken");
            } else
                user.setEmail(email);
        }
    }
}
