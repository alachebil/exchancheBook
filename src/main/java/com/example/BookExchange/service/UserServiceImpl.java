package com.example.BookExchange.service;

import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.UserP;
import com.example.BookExchange.repository.RoleRepository;
import com.example.BookExchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional // mech mafhoumaa chnyaaa
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserP userP=userRepository.findByUsername(username);
       if(userP==null){
           throw new UsernameNotFoundException("user not found in dataBase !!");
       }

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        userP.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName())); });
        return new org.springframework.security.core.userdetails.User(userP.getUsername(),userP.getPassword(),authorities);
    }


    @Override
    public UserP saveUser(UserP userP) {
      //  log.info("saving user");
        userP.setPassword(passwordEncoder.encode(userP.getPassword()));
        return userRepository.save(userP);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserP userP = userRepository.findByUsername(username);
        Role role=roleRepository.findByName(roleName);
        userP.getRoles().add(role); // najem naamalha haka 9al khater notation Transactional

    }

    @Override
    public UserP getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserP> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) throws IllegalAccessException {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalAccessException("user with id " + userId + " is not exist ");
        } else
            userRepository.deleteById(userId);
        System.out.println("the student is deleted successfully");

    }

    @Override
    @Transactional
    public void updateUser(Long userId, String name, String username, String password) throws IllegalAccessException {

        UserP user = userRepository.findById(userId).orElseThrow(() -> new IllegalAccessException("user with id " + userId + " is not exist "));
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }


        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)) {
            UserP userP = userRepository.findByUsername(username);
            if (userP!=null) {
                throw new IllegalAccessException("username is taken");
            } else
                user.setUsername(username);
        }

        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
    }


}
