package com.example.tasks_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;    
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks () {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTaskByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task addTask(Long userId, Task task) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existing = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        existing.setTitle(updatedTask.getTitle());
        existing.setStatus(updatedTask.getStatus());
        return taskRepository.save(existing);
    }
}
