package com.mobylab.springbackend.service;


import com.mobylab.springbackend.entity.Task;
import com.mobylab.springbackend.entity.User;
import com.mobylab.springbackend.exception.entities.TaskException;
import com.mobylab.springbackend.exception.entities.UserException;
import com.mobylab.springbackend.repository.TaskRepository;
import com.mobylab.springbackend.repository.UserRepository;
import com.mobylab.springbackend.service.dto.entity.task.TaskRequestDto;
import com.mobylab.springbackend.service.dto.entity.task.TaskResponseDto;
import com.mobylab.springbackend.service.dto.entity.user.UserResponseDto;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDto getById(UUID id) {
        Optional<Task> maybeTask = taskRepository.findById(id);

        if (maybeTask.isEmpty()) {
            logger.error("Cannot find task by id.");
            throw new UserException("Cannot find task by id.");
        } else {
            return createDto(maybeTask.get());
        }
    }

    public List<TaskResponseDto> getByCreatorId(UUID creatorId) {
        User creator = this.getUserById(creatorId);

        return taskRepository.findAllByCreator(creator).stream().map(this::createDto).toList();
    }

    public List<TaskResponseDto> getByAssigneeId(UUID assigneeId) {
        User assignee = this.getUserById(assigneeId);

        return taskRepository.findAllByAssignee(assignee).stream().map(this::createDto).toList();
    }

    public TaskResponseDto create(TaskRequestDto taskRequestDto) {
        Task task = new Task();

        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus("TODO");
        task.setDeadline(taskRequestDto.getDeadline());
        task.setCreator(this.getUserById(taskRequestDto.getCreatorId()));
        task.setAssignee(this.getUserById(taskRequestDto.getAssigneeId()));

        taskRepository.save(task);

        return createDto(task);
    }

    public TaskResponseDto update(UUID id, TaskRequestDto taskRequestDto) {
        Task task = this.getTaskById(id);

        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setDeadline(taskRequestDto.getDeadline());
        task.setCreator(this.getUserById(taskRequestDto.getCreatorId()));
        task.setAssignee(this.getUserById(taskRequestDto.getAssigneeId()));

        taskRepository.save(task);

        return createDto(task);
    }

    public TaskResponseDto complete(UUID id) {
        Task task = this.getTaskById(id);

        task.setStatus("DONE");

        taskRepository.save(task);

        return createDto(task);
    }

    public void delete(UUID id) {
        Task task = this.getTaskById(id);

        taskRepository.delete(task);
    }

    private User getUserById(UUID userId) {
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isEmpty()) {
            logger.error("Cannot find user by id.");
            throw new UserException("Cannot find user by id.");
        } else {
            return maybeUser.get();
        }
    }

    private Task getTaskById(UUID taskId) {
        Optional<Task> maybeTask = taskRepository.findById(taskId);

        if (maybeTask.isEmpty()) {
            logger.error("Cannot find task by id.");
            throw new UserException("Cannot find task by id.");
        } else {
            return maybeTask.get();
        }
    }

    private TaskResponseDto createDto(Task task) {
        TaskResponseDto taskDto = new TaskResponseDto();

        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setCreatorId(task.getCreator().getId());
        taskDto.setAssigneeId(task.getAssignee().getId());
        taskDto.setStatus(task.getStatus());

        return taskDto;
    }
}
