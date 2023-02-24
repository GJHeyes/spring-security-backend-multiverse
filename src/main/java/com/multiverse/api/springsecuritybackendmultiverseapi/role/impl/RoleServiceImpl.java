package com.multiverse.api.springsecuritybackendmultiverseapi.role.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipes;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleService;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;

import java.util.List;

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
    public Roles addRole(Roles role) {

        return roleRepository.save(role);
    }

    @Override
    public Roles updateRole(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public Roles deleteRole(int roleId) throws Exception {
        Roles deletedRole = (null);
        try {
            deletedRole = roleRepository.findById(roleId).orElse(null);
            if (deletedRole == null) {
                throw new Exception("role dose not exist");

            } else {
                roleRepository.deleteById(roleId);
            }
        } catch (Exception ex) {
            throw ex;

        } return deletedRole;
    }
}


