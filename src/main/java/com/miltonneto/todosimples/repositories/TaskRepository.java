package com.miltonneto.todosimples.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miltonneto.todosimples.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   List<Task> findByUser_Id(long id);

}

