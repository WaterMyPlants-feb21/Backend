package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.Role;

public interface RoleService
{
    Role findRoleById(long id);
    Role save(Role role);
    public void deleteAll();
}
