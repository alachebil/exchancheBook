package com.example.BookExchange.dto;

import com.example.BookExchange.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper {

    private final ModelMapper mapper;

    public UserDtoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

//    @Override
//    public UserDTO apply(UserP userP) {
//        return new UserDTO(userP.getName(),userP.getUsername(),userP.getTel());
//
//    }

    public UserDTO UserToDTO(User user) {
        // when similar source object is provided

        return this.mapper.map(user, UserDTO.class);
    }

    public User DTOToUser(UserDtoCreation userDtoCreation) {
        // when similar source object is provided

        return this.mapper.map(userDtoCreation, User.class);
    }







}
