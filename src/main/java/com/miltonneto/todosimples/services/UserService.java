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

    public User findById(Long id){  //optional , caso o usuario esteja vazio.
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(  //exeption padrao interrompe a aplicacao , RuntimeExeption nao. Retorna o usuario se esle estiver preenchido se nao faz uma throwExeption
            "Usuario nao encontrado! ID:" + id + "Tipo: " + User.class.getName()
        ));
    }

    @Transactional
    public User create (User obj ){ 
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir pois ha entidades relacionadas !!! ");
        }
    }

}
