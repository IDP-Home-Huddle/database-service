package com.mobylab.springbackend.service;

import com.mobylab.springbackend.entity.Task;
import com.mobylab.springbackend.entity.User;
import com.mobylab.springbackend.exception.entities.UserException;
import com.mobylab.springbackend.repository.UserRepository;
import com.mobylab.springbackend.service.dto.entity.user.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getById(UUID id) {
        Optional<User> maybeUser = userRepository.findById(id);

        if (maybeUser.isEmpty()) {
            logger.error("Cannot find user by id.");
            throw new UserException("Cannot find user by id.");
        } else {
            return createDto(maybeUser.get());
        }
    }

    public UserResponseDto getByEmail(String email) {
        Optional<User> maybeUser = userRepository.findUserByEmail(email);

        if (maybeUser.isEmpty()) {
            logger.error("Cannot find user by email.");
            throw new UserException("Cannot find user by email.");
        } else {
            return createDto(maybeUser.get());
        }
    }

    public List<UserResponseDto> getByFamilyId(UUID familyId) {
        // TOD0
    }

    private UserResponseDto createDto(User user) {
        UserResponseDto userDto = new UserResponseDto();

        userDto.setId(user.getId());
        userDto.setFamilyId(user.getFamilyId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCreatedTasksIds(user.getCreatedTasks().stream().map(Task::getId).toList());
        userDto.setAssignedTasksIds(user.getAssignedTasks().stream().map(Task::getId).toList());

        return userDto;
    }
}
