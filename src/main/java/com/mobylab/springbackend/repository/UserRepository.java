package com.mobylab.springbackend.repository;

import com.mobylab.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsUserByEmail(String email);
    Optional<User> findUserByEmail(String email);
    List<User> findByFamilyId(UUID familyId);
}
