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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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


}
