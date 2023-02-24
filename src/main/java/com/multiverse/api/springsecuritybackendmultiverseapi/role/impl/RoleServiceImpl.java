package com.multiverse.api.springsecuritybackendmultiverseapi.role.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleService;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }
    @Override
    public Role getRoleById(int roleId) {
        return roleRepository.findById(roleId).orElse(null);

    }

    @Override
    public Role addRole(Role role) {
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public Role deleteRole(int roleId) throws Exception {
        return null;
    }
}
