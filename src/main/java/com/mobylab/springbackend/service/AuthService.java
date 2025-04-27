package com.mobylab.springbackend.service;

import com.mobylab.springbackend.entity.Role;
import com.mobylab.springbackend.entity.User;
import com.mobylab.springbackend.repository.RoleRepository;
import com.mobylab.springbackend.repository.UserRepository;
import com.mobylab.springbackend.service.dto.auth.LoginRequestDto;
import com.mobylab.springbackend.service.dto.auth.RegisterRequestDto;
import com.mobylab.springbackend.service.dto.auth.RegisterWithFamilyIdRequestDto;
import com.mobylab.springbackend.exception.auth.AuthException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerWithoutFamilyId(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsUserByEmail(registerRequestDto.getEmail())) {
            logger.error("Email already used.");
            throw new AuthException("Email already used.");
        }

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByName(registerRequestDto.getRole()).get());

        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setRoles(roles);
        user.setPassword(registerRequestDto.getPassword());

        // generate new familyId for user

        userRepository.save(user);
    }

    public void registerWithFamilyId(RegisterWithFamilyIdRequestDto registerRequestDto) {
        if (userRepository.existsUserByEmail(registerRequestDto.getEmail())) {
            logger.error("Email already used.");
            throw new AuthException("Email already used.");
        }

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByName(registerRequestDto.getRole()).get());

        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setRoles(roles);
        user.setPassword(registerRequestDto.getPassword());
        user.setFamilyId(registerRequestDto.getFamilyId());

        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto) {
        Optional<User> maybeUser = userRepository.findUserByEmail(loginRequestDto.getEmail());

        if (maybeUser.isEmpty()) {
            logger.error("Wrong email.");
            throw new AuthException("Wrong email.");
        }
    }
}
