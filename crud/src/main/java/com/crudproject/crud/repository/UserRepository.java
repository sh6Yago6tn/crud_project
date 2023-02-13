package com.crudproject.crud.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.crudproject.crud.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);
}
