package pl.lukk.service;

import java.util.List;

import pl.lukk.entity.Role;

public interface RoleService
{

    Role findByName(String name);

    List<Role> findAll();

    Role findOneById(Long id);

    void removeRole(Long id);

}
