package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import java.util.List;

public interface RoleService {
    public List<Roles> getAllRoles();
    public Roles getUserById(int userId);
    public Roles addOrUpdateRole(Roles role);
    public Roles deleteRole(int roleId) throws Exception;

    Roles getRoleById(int roleId);
}

//service layer
