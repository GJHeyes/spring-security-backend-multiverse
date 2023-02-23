package com.multiverse.api.springsecuritybackendmultiverseapi.role;

import com.multiverse.api.springsecuritybackendmultiverseapi.role.Roles;
import com.multiverse.api.springsecuritybackendmultiverseapi.role.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping()
    public ResponseEntity<List<Roles>> getAllRoles(){
        List<Roles> roles = null;
        try{
            roles = roleService.getAllRoles();

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRolesById(@PathVariable("id") int roleId){
        Roles roles = null;
        try{
            roles = roleService.getRoleById(roleId);

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Roles> addOrUpdate(@RequestBody Roles role){
        Roles roles = null;
        try{
        roles = roleService.addOrUpdateRole(role);

        }catch(Exception ex){
        ex.getMessage();
        }
        return new ResponseEntity<>(roles,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Roles> addOrUpdate(@PathVariable ("id") int roleId){
        Roles roles = null;
        try{
            roles = roleService.deleteRole(roleId);

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}