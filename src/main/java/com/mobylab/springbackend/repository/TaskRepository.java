package com.mobylab.springbackend.repository;

import com.mobylab.springbackend.entity.Task;
import com.mobylab.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByCreator(User creator);
    List<Task> findAllByAssignee(User assignee);
}