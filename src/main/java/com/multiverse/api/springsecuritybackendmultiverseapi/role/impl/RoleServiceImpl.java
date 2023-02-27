package com.multiverse.api.springsecuritybackendmultiverseapi.role.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleService;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role, int roleId) {
        Role optionalRole = roleRepository.findById(roleId).orElseGet(Role::new);
        if(optionalRole.getName() != null){
            optionalRole.setResponsibilities(role.getResponsibilities());
        }
        return optionalRole;
    }

    @Override
    public Role deleteRole(int roleId) throws Exception {
        Role role = roleRepository.findById(roleId).orElseGet(Role::new);
        roleRepository.deleteById(roleId);
        return role;
    }
}
