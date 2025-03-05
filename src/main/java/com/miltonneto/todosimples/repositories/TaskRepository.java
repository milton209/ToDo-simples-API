package com.miltonneto.todosimples.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miltonneto.todosimples.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   List<Task> findById(long id);

//@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
//List <Task> findByUser_id(@Param("id") Long id);

//@Query()
//List <Task> findByUser_id(@Param("id") Long id);


//@Query(value = "SELECT * FROM task t WHERE t.user_id = :id" , nativeQuery = true)
//List <Task> findByUser_id(@Param("id") Long id);


}

