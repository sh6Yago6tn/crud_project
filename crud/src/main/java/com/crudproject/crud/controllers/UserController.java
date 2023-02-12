package com.crudproject.crud.controllers;

import com.crudproject.crud.interfaces.CreateUser;
import com.crudproject.crud.interfaces.UpdateUser;
import com.crudproject.crud.models.UserModel;
import com.crudproject.crud.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<UserModel>> getAllUsers(){
        Iterable<UserModel> users = userService.findAllUser();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable UUID id){
        UserModel obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserModel obj){
        this.userService.createUser(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserModel obj, UUID id){
        obj.setId(id);
        this.userService.updateUser(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}