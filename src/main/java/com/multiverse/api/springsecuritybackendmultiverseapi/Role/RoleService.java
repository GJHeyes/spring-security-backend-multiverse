package com.multiverse.api.springsecuritybackendmultiverseapi.Role;

import java.util.List;

public interface RoleService {
    public List<Roles> getAllRoles();
    public Roles getUserById(int userId);
    public Roles addOrUpdateRole(Roles role);
    public Roles deleteRole(int roleId);

}

//service layer
