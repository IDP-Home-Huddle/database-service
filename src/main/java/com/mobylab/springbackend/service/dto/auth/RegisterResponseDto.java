package com.mobylab.springbackend.service.dto.auth;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterResponseDto {
    private UUID id;
    private String username;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
