package com.mobylab.springbackend.service.dto.entity.user;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserResponseDto {
    private UUID id;
    private UUID familyId;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private String email;
    private List<UUID> createdTasksIds;
    private List<UUID> assignedTasksIds;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFamilyId() {
        return familyId;
    }

    public void setFamilyId(UUID familyId) {
        this.familyId = familyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UUID> getCreatedTasksIds() {
        return createdTasksIds;
    }

    public void setCreatedTasksIds(List<UUID> createdTasksIds) {
        this.createdTasksIds = createdTasksIds;
    }

    public List<UUID> getAssignedTasksIds() {
        return assignedTasksIds;
    }

    public void setAssignedTasksIds(List<UUID> assignedTasksIds) {
        this.assignedTasksIds = assignedTasksIds;
    }
}
