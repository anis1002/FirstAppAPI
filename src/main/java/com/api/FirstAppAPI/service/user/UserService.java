package com.api.FirstAppAPI.service.user;
import com.api.FirstAppAPI.converter.UserConverter;
import com.api.FirstAppAPI.dto.UserDto;
import com.api.FirstAppAPI.model.User;
import com.api.FirstAppAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;



    @Override
    public List<UserDto> getAllUsers(String firstname, String lastname, String email,int page,int size) {

        return userRepository
                .findAll().stream()
//                .filter(u -> firstname == null || u.getFirstname().toLowerCase().contains(firstname.toLowerCase()))
//                .filter(u -> lastname == null || u.getLastname().toLowerCase().contains(lastname.toLowerCase()))
                .map(userConverter::toDto)
                        .collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
//        return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
        return userConverter.toDto(user);
    }

    @Override
    public Long createUser(UserDto user) {
        if(user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 8) {
            throw new RuntimeException("Password length should be at least 8 characters");
        }
                User newUser = userConverter.toEntity(user);
//              return  userConverter.toDto(userRepository.save(newUser));
                 userRepository.save(newUser);
        return newUser.getId() ;
    }

    @Override
    public Long updateUser(Long id, UserDto updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setEmail(updatedUser.getEmail());
//        existingUser.setPassword(updatedUser.getPassword());
        userRepository.save(existingUser);
        return existingUser.getId();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
