package com.example.notepad.service.role;

import com.example.notepad.model.Role;
import com.example.notepad.service.exception.role.RoleNotFoundException;

public interface RoleService {

    Role saveRole(Role role);

    Role updateRole(String name, Role role) throws RoleNotFoundException;

    Role updateRole(Long id, Role role) throws RoleNotFoundException;

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role getRoleById(Long id) throws RoleNotFoundException;

    void deleteRoleById(Long id);

    void deleteRoleByName(String name);
}
