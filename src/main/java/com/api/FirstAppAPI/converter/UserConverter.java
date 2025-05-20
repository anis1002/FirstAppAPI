package com.api.FirstAppAPI.converter;

import com.api.FirstAppAPI.dto.UserDto;
import com.api.FirstAppAPI.model.User;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public  UserDto toDto(User user) {
        if (user == null) return null;
//            return new UserDto(
//                    user.getId(),
//                    user.getFirstname(),
//                    user.getLastname(),
//                    user.getEmail()
//            );
        return UserDto.builder().id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

    public  User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId()); // Optional for creation
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        return user;
    }
}


