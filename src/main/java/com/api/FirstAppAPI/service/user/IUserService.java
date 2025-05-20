package com.api.FirstAppAPI.service.user;

import com.api.FirstAppAPI.dto.UserDto;
import com.api.FirstAppAPI.model.User;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers(String firstname, String lastname, String email,int page,int size);
    UserDto getUserById(Long id);
    Long createUser(UserDto user);
    Long updateUser(Long id, UserDto updatedUser);
    void deleteUser(Long id);
}
