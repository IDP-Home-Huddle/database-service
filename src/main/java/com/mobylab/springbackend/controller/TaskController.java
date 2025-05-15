package com.mobylab.springbackend.controller;

import com.mobylab.springbackend.service.TaskService;
import com.mobylab.springbackend.service.dto.entity.task.TaskRequestDto;
import com.mobylab.springbackend.service.dto.entity.task.TaskResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController implements SecuredRestController{
    @Autowired
    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getById")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<TaskResponseDto> getById(@RequestParam UUID id) {
        logger.info("Request to get task by id {}", id);
        TaskResponseDto taskResponseDto = taskService.getById(id);
        logger.info("Successfully retrieved task by id {}", id);

        return ResponseEntity.ok(taskResponseDto);
    }

    @GetMapping("/getByCreatorId")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<List<TaskResponseDto>> getByCreatorId(@RequestParam UUID creatorId) {
        logger.info("Request to get task list by creatorId {}", creatorId);
        List<TaskResponseDto> taskResponseDtoList = taskService.getByCreatorId(creatorId);
        logger.info("Successfully retrieved task list by creatorId {}", creatorId);

        return ResponseEntity.ok(taskResponseDtoList);
    }

    @GetMapping("/getByAssigneeId")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<List<TaskResponseDto>> getByAssigneeId(@RequestParam UUID assigneeId) {
        logger.info("Request to get task list by assigneeId {}", assigneeId);
        List<TaskResponseDto> taskResponseDtoList = taskService.getByAssigneeId(assigneeId);
        logger.info("Successfully retrieved task list by assigneeId {}", assigneeId);

        return ResponseEntity.ok(taskResponseDtoList);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto taskRequestDto) {
        logger.info("Request to create new task.");
        TaskResponseDto taskResponseDto = taskService.create(taskRequestDto);
        logger.info("Successfully created new task with id {}.", taskResponseDto.getId());
        
        return ResponseEntity.ok(taskResponseDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<TaskResponseDto> update(@RequestParam UUID id, @RequestBody TaskRequestDto taskRequestDto) {
        logger.info("Request to update task with id {}.", id);
        TaskResponseDto taskResponseDto = taskService.update(id, taskRequestDto);
        logger.info("Successfully updated task with id {}.", id);
        
        return ResponseEntity.ok(taskResponseDto);
    }

    @PutMapping("/complete")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<TaskResponseDto> complete(@RequestParam UUID id) {
        logger.info("Request to complete task with id {}.", id);
        TaskResponseDto taskResponseDto = taskService.complete(id);
        logger.info("Successfully completed task with id {}.", id);

        return ResponseEntity.ok(taskResponseDto);
    }
    
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('PARENT') or hasAuthority('CHILD') or hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        logger.info("Request to delete task with id {}.", id);
        taskService.delete(id);
        logger.info("Successfully deleted task with id {}.", id);
        
        return ResponseEntity.ok().build();
    }
}
