package com.example.notepad.dao;

import com.example.notepad.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> getRoleByName(String name);

    void deleteRoleByName(String name);
}
