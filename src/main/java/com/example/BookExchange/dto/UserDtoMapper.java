package com.example.BookExchange.dto;

import com.example.BookExchange.entity.UserP;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper implements Function<UserP, UserDTO> {
    @Override
    public UserDTO apply(UserP userP) {
        return new UserDTO(userP.getName(),userP.getUsername(),userP.getTel());

    }
}
