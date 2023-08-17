package com.example.BookExchange.service;

import com.example.BookExchange.dto.UserDTO;
import com.example.BookExchange.dto.UserDtoCreation;
import com.example.BookExchange.dto.UserDtoMapper;
import com.example.BookExchange.entity.Role;
import com.example.BookExchange.entity.User;
import com.example.BookExchange.repository.RoleRepository;
import com.example.BookExchange.repository.UserRepository;
import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // mech mafhoumaa chnyaaa
public class UserServiceImpl implements UserService, UserDetailsService {

/********************   teba3 sending sms    ******************/
//
//    public static final String ACCOUNT_SID ="AC3b79629f5713befa3d9d21934642db85";
//    public static final String AUTH_TOKEN ="a2b93334fdd84131b97985e5f63ae1a1";


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found in dataBase !!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


//    @Override
//    public UserP saveUser(UserP userP) {
//      //  log.info("saving user");
//        userP.setPassword(passwordEncoder.encode(userP.getPassword()));
//        return userRepository.save(userP);
//
//    }

    @Override
    public User saveUser(UserDtoCreation userDtoCreation) {
        //  log.info("saving user");
        User Userexists = userRepository.findByUsername(userDtoCreation.getUsername());
        if (Userexists != null) {
            throw new IllegalStateException("username is already taken.");
        } else {
            userDtoCreation.setPassword(passwordEncoder.encode(userDtoCreation.getPassword()));
            User user = userDtoMapper.DTOToUser(userDtoCreation);

            /**************** lel ssmmsss ki tzid compte **********/

//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//       Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+21696212001"), // najem nbadlou dynamic userDtoCreation.getTel()
//                new com.twilio.type.PhoneNumber("+16189360915"),
//                        "Hi there votre compte avec username"+userDtoCreation.getUsername()+ "est ajouté avec succee")
//            .create();
//
//        System.out.println(message.getSid());


            return userRepository.save(user);
        }
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role); // najem naamalha haka 9al khater notation Transactional

    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper::UserToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) throws IllegalAccessException {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalAccessException("user with id " + userId + " is not exist ");
        } else
            userRepository.deleteById(userId);
        System.out.println("the user is deleted successfully");

    }

    @Override
    @Transactional
    public void updateUser(Long userId, String name, String username, String password) throws IllegalAccessException {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalAccessException("user with id " + userId + " is not exist "));
        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }


        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)) {
            User userP = userRepository.findByUsername(username);
            if (userP != null) {
                throw new IllegalAccessException("username is taken");
            } else
                user.setUsername(username);
        }

        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
    }


}
