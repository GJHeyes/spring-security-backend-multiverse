package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import java.util.List;

public interface RoleService {
    public List<Roles> getAllRoles();
    public Roles getRoleById(int roleId);
    public Roles addOrUpdateRole(Roles role);
    public Roles deleteRole(int roleId) throws Exception;

}

//service layer
