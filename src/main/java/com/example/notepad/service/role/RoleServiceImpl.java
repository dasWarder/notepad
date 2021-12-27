package com.example.notepad.service.role;

import com.example.notepad.dao.RoleRepository;
import com.example.notepad.model.Role;
import com.example.notepad.service.exception.role.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role saveRole(Role role) {

    log.info("In RoleServiceImpl.saveRole - Store a new role");
    return roleRepository.save(role);
  }

  @Override
  public Role updateRole(String name, Role role) throws RoleNotFoundException {

    log.info("In RoleServiceImpl.updateRole - Update role by name = {}", name);

    Role roleByName = this.getRoleByName(name);
    role.setId(roleByName.getId());

    return roleRepository.save(role);
  }

  @Override
  public Role updateRole(Long id, Role role) throws RoleNotFoundException {

    log.info("In RoleServiceImpl.updateRole - Update role by id = {}", id);

    Role roleById = this.getRoleById(id);
    role.setId(roleById.getId());

    return roleRepository.save(role);
  }

  @Override
  public Role getRoleByName(String name) throws RoleNotFoundException {

    log.info("In RoleServiceImpl.getRoleByName - Get role by name = {}", name);

    return roleRepository
        .getRoleByName(name)
        .orElseThrow(
            () -> new RoleNotFoundException(String.format("Role with name = %s not found", name)));
  }

  @Override
  public Role getRoleById(Long id) throws RoleNotFoundException {

    log.info("In RoleServiceImpl.getRoleById - Get role by id = {}", id);

    return roleRepository
        .findById(id)
        .orElseThrow(
            () -> new RoleNotFoundException(String.format("Role with id = %d not found", id)));
  }

  @Override
  public void deleteRoleById(Long id) {

    log.info("In RoleServiceImpl.deleteRoleById - Delete role by id = {}", id);
    roleRepository.deleteById(id);
  }

  @Override
  public void deleteRoleByName(String name) {

    log.info("In RoleServiceImpl.deleteRoleByName - Delete role by name = {}", name);
    roleRepository.deleteRoleByName(name);
  }
}
