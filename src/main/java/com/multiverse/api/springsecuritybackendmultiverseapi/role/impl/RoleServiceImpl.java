package com.multiverse.api.springsecuritybackendmultiverseapi.role.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleService;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Roles> getAllRoles() {
        return (List<Roles>) roleRepository.findAll();
    }
    @Override
    public Roles getRoleById(int roleId) {
        return roleRepository.findById(roleId).orElse(null);

    }
    @Override
    public Roles addOrUpdateRole(Roles role) {
        return null;
    }

    @Override
    public Roles deleteRole(int roleId) throws Exception {
        return null;
    }
}
