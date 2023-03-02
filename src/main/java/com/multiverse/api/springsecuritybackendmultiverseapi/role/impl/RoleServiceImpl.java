package com.multiverse.api.springsecuritybackendmultiverseapi.role.impl;

import com.multiverse.api.springsecuritybackendmultiverseapi.auth.Extract;
import com.multiverse.api.springsecuritybackendmultiverseapi.exception.CustomError;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleRepository;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.RoleService;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.Role;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.User;
import com.multiverse.api.springsecuritybackendmultiverseapi.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private Extract extract;
    private UserRepository userRepository; //caps is the class lower case is the instance
    @Override
    public ResponseEntity<List<Role>> getAllRoles(HttpServletRequest token) throws CustomError{
        User user = userRepository.findByEmail(extract.emailFromJwt(token)).orElseThrow(()->new CustomError("User not found"));
        return extract.listOfRole(user);
    }

    @Override
    public Role getRoleById(int roleId) throws CustomError {
        return roleRepository.findById(roleId).orElseThrow(()->new CustomError("User not found"));

    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role, int roleId) throws CustomError{
        Role optionalRole = roleRepository.findById(roleId).orElseThrow(()->new CustomError("Role not found"));
        if(optionalRole.getName() != null){
            optionalRole.setResponsibilities(role.getResponsibilities());
        }
        return optionalRole;
    }

    @Override
    public Role deleteRole(int roleId) throws CustomError {
        Role role = roleRepository.findById(roleId).orElseThrow(()->new CustomError("Role not found"));
        roleRepository.deleteById(roleId);
        return role;
    }
}
