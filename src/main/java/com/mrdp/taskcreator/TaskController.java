package com.mrdp.taskcreator;

import com.mrdp.taskcreator.task.dao.Task;
import com.mrdp.taskcreator.task.dto.TaskCreateDto;
import com.mrdp.taskcreator.task.dto.TaskDto;
import com.mrdp.taskcreator.task.dto.TaskEditDto;
import com.mrdp.taskcreator.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("user")
    public ResponseEntity<List<TaskDto>> getAllForUser(Authentication authentication){
        return ResponseEntity.ok(taskService.findAllForUser((String) authentication.getPrincipal()));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto taskCreateDto, Authentication authentication){
        return ResponseEntity.ok(taskService.createTask(taskCreateDto, (String) authentication.getPrincipal()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> editTask(@PathVariable("id") String id, @RequestBody TaskEditDto taskEditDto){
        return ResponseEntity.ok(taskService.editTask(id, taskEditDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") String id){
        return ResponseEntity.of(taskService.getTask(id));
    }
}
