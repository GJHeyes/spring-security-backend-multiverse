package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles(HttpServletRequest token);

    ;//if user is not an admin return "grunt" - return all if user is an admin
    public Role getRoleById(int roleId);
    public Role addRole(Role role);
    public Role updateRole(Role role, int recipeID);
    public Role deleteRole(int roleId) throws Exception;

}

//service layer
