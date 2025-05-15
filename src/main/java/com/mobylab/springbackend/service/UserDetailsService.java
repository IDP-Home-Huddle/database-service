package com.mobylab.springbackend.service;

import com.mobylab.springbackend.entity.Role;
import com.mobylab.springbackend.entity.User;
import com.mobylab.springbackend.exception.entities.UserException;
import com.mobylab.springbackend.repository.UserRepository;
import com.mobylab.springbackend.service.dto.entity.user.UserDetailsResponseDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsResponseDto getByEmail(String email) {
        Optional<User> maybeUser = userRepository.findUserByEmail(email);

        if (maybeUser.isEmpty()) {
            logger.error("Cannot find user by id.");
            throw new UserException("Cannot find user by id.");
        } else {
            return createDto(maybeUser.get());
        }
    }

    private UserDetailsResponseDto createDto(User user) {
        UserDetailsResponseDto userDetailsDto = new UserDetailsResponseDto();

        userDetailsDto.setEmail(user.getEmail());
        userDetailsDto.setPassword(user.getPassword());
        userDetailsDto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return userDetailsDto;
    }
}
