package com.mrdp.taskcreator.task.repository;

import com.mrdp.taskcreator.task.dao.Task;
import com.mrdp.taskcreator.user.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOwner(UserEntity user);
}
