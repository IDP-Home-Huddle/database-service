package com.mobylab.springbackend.controller;

import com.mobylab.springbackend.service.UserDetailsService;
import com.mobylab.springbackend.service.dto.entity.user.UserDetailsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userproxy")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<UserDetailsResponseDto> getUserByEmail(String email) {
        UserDetailsResponseDto userDetailsResponseDto = userDetailsService.getByEmail(email);
        return ResponseEntity.ok(userDetailsResponseDto);
    }
}
