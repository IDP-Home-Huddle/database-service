package com.mobylab.springbackend.service;


import com.mobylab.springbackend.exception.entities.TaskException;
import com.mobylab.springbackend.repository.TaskRepository;
import com.mobylab.springbackend.service.dto.entity.task.TaskRequestDto;
import com.mobylab.springbackend.service.dto.entity.task.TaskResponseDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDto getById(UUID id) {

    }

    public List<TaskResponseDto> getByFamilyId(UUID familyId) {

    }

    public List<TaskResponseDto> getByCreatorId(UUID creatorId) {

    }

    public List<TaskResponseDto> getByAssigneeId(UUID assigneeId) {

    }

    public TaskResponseDto create(TaskRequestDto taskRequestDto) {

    }

    public TaskResponseDto update(UUID id, TaskRequestDto taskRequestDto) {

    }

    public TaskResponseDto complete(UUID id) {

    }

    public void delete(UUID id) {

    }

    // create taskDto mapper
}
