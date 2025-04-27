package com.mobylab.springbackend.service.dto.entity.user;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsResponseDto {
    private String email;
    private String password;
    private List<String> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
