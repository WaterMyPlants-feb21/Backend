package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Role;

public interface RoleService
{
    Role findRoleById(long id);
    Role findByName(String name);
    Role save(Role role);
    public void deleteAll();
}
