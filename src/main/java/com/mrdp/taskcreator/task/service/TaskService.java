package com.mrdp.taskcreator.task.service;

import com.mrdp.taskcreator.task.TaskMapper;
import com.mrdp.taskcreator.task.dao.Task;
import com.mrdp.taskcreator.task.dto.TaskCreateDto;
import com.mrdp.taskcreator.task.dto.TaskDto;
import com.mrdp.taskcreator.task.dto.TaskEditDto;
import com.mrdp.taskcreator.task.repository.TaskRepository;
import com.mrdp.taskcreator.user.dao.UserEntity;
import com.mrdp.taskcreator.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public List<TaskDto> findAll(){
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(taskMapper::mapDto).toList();
    }

    public Task createTask(TaskCreateDto taskCreateDto, String username){
        UserEntity user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);

        Task task = new Task();
        task.setDate(taskCreateDto.getDate());
        task.setDescription(taskCreateDto.getDescription());
        task.setName(taskCreateDto.getName());
        task.setOwner(user);
        task.setFinished(false);

        return taskRepository.save(task);
    }

    public Task editTask(String id, TaskEditDto taskEditDto) {
        Task task = taskRepository.findById(Long.parseLong(id)).orElseThrow(RuntimeException::new);

        task.setFinished(taskEditDto.isFinished());
        task.setName(taskEditDto.getName());
        task.setDescription(taskEditDto.getDescription());
        task.setDate(taskEditDto.getDate());

        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(Long.parseLong(id));
    }

    public Optional<Task> getTask(String id) {
        return taskRepository.findById(Long.parseLong(id));
    }

    public List<TaskDto> findAllForUser(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        List<Task> tasks = taskRepository.findAllByOwner(user);

        return tasks.stream().map(taskMapper::mapDto).toList();
    }
}
