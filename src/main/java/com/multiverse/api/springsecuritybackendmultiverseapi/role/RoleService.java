package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleById(int roleId);
    public Role addRole(Role role);
    public Role updateRole(Role role, int recipeID);
    public Role deleteRole(int roleId) throws Exception;

}

//service layer
