package com.crudproject.crud.services;

import com.crudproject.crud.models.UserModel;
import com.crudproject.crud.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserModel> findAllUser() {
        return this.userRepository.findAll();
    }

    public UserModel findById(UUID id) {
        Optional<UserModel> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Transactional
    public UserModel createUser(UserModel obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public UserModel updateUser(UserModel obj){
        UserModel newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        this.userRepository.save(newObj);
        return newObj;
    }

    @Transactional
    public void deleteUser(UUID id){
        try {
            this.userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new IllegalArgumentException("Invalid operation, this id have entities related! id-" + id);
        }
    }


}
