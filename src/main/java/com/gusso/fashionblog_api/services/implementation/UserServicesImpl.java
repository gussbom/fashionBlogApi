package com.gusso.fashionblog_api.services.implementation;

import com.gusso.fashionblog_api.dto.request.UserRequestDto;
import com.gusso.fashionblog_api.dto.response.UserResponseDto;
import com.gusso.fashionblog_api.entities.User;
import com.gusso.fashionblog_api.exceptions.CustomExceptions;
import com.gusso.fashionblog_api.utils.Mapper;
import com.gusso.fashionblog_api.repositories.UserRepository;
import com.gusso.fashionblog_api.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public UserServicesImpl(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        Optional<User> checkUsernameAvailability
                = userRepository.findByUsername(request.getUsername());

        if(checkUsernameAvailability.isPresent()){
            throw new CustomExceptions("User already exists", HttpStatus.NOT_FOUND);
        }

        User user =new User();
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
        User newUser = userRepository.save(user);

        return Mapper.userResponse(newUser);
    }

    @Override
    public UserResponseDto loginUser(UserRequestDto request) {

        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if(user.isPresent()){
            httpSession.setAttribute("username", request.getUsername());
            return Mapper.userResponse(user.get());
        }
        throw new CustomExceptions("User does not exists", HttpStatus.NOT_FOUND);
    }
}
