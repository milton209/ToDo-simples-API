package com.miltonneto.todosimples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miltonneto.todosimples.models.User;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {
    
}
