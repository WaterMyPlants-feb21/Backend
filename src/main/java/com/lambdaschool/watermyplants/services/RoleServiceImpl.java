package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.exceptions.ResourceFoundException;
import com.lambdaschool.watermyplants.exceptions.ResourceNotFound;
import com.lambdaschool.watermyplants.models.Role;
import com.lambdaschool.watermyplants.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleById(long id)
    {
        return roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Role "+id + " not found"));
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers()
            .size() > 0)
        {
            throw new ResourceFoundException("User Roles are not updated through Role.");
        }

        roleRepository.save(role);
        return role;
    }

    @Override
    public void deleteAll()
    {
        roleRepository.deleteAll();
    }
}
