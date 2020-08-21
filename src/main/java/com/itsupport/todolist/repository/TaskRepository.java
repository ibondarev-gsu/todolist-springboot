package com.itsupport.todolist.repository;

import com.itsupport.todolist.entities.Task;
import com.itsupport.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//    Optional<Collection<Task>> findTaskByUserAndDateOfCreationBetween(User user, LocalDateTime start, LocalDateTime end);
//    Optional<Collection<Task>> findTaskByUser(User user);
    void delete(Task task);
}
