package com.miltonneto.todosimples.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.miltonneto.todosimples.models.User;
import com.miltonneto.todosimples.repositories.UserRepository;

    @Service
    public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){  
        //optional , caso o usuario esteja vazio.
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException( "Usuario nao encontrado! ID:" + id + "Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create (User obj ){ 
        try{
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
        } catch(Exception i){
            throw new RuntimeException("Usuario ja existente!!!");
        }
    }
    

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir pois ha entidades relacionadas !!! ");
        }
    }


}

