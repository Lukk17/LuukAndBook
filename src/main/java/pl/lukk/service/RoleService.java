package pl.lukk.service;

import java.util.List;

import pl.lukk.entity.Role;

public interface RoleService
{

    /**
     * Give Role with same name as given.
     * 
     * @param name      Name of role.
     * @return          Return one Role.
     */
    Role findByName(String name);

    /**
     * Give all of Roles in database.
     * 
     * @return  Return list of all Roles.
     */
    List<Role> findAll();

    /**
     * Give Role with given ID.
     * 
     * @param id    ID of Role.
     * @return      Return one Role.
     */
    Role findOneById(Long id);

    /**
     * Remove Role with given ID.
     * 
     * @param id    ID of Role to remove.
     */
    void removeRole(Long id);

}
