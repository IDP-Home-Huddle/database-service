package com.mobylab.springbackend.controller;

import com.mobylab.springbackend.service.UserService;
import com.mobylab.springbackend.service.dto.entity.user.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController implements SecuredRestController{
    @Autowired
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> getById(UUID id) {
        logger.info("Request to get user by id {}", id);
        UserResponseDto userResponseDto = userService.getById(id);
        logger.info("Successfully retrieved user by id {}", id);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/getByEmail")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> getByEmail(String email) {
        logger.info("Request to get user by email {}", email);
        UserResponseDto userResponseDto = userService.getByEmail(email);
        logger.info("Successfully retrieved user by email {}", email);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/getByFamilyId")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getByFamilyId(UUID familyId) {
        logger.info("Request to get user list by familyId {}", familyId);
        List<UserResponseDto> userResponseDtoList = userService.getByFamilyId(familyId);
        logger.info("Successfully retrieved user list by familyId {}", familyId);

        return ResponseEntity.ok(userResponseDtoList);
    }
}
