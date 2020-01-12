package pl.lukk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.lukk.entity.Role;
import pl.lukk.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name)
    {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }

    @Override
    public Role findOneById(Long id)
    {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void removeRole(Long id)
    {
        roleRepository.delete(roleRepository.findById(id).orElse(null));
    }
}
