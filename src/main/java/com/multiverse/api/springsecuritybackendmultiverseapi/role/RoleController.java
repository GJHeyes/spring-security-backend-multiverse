package com.multiverse.api.springsecuritybackendmultiverseapi.role;

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
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> roles = null;
        try{
            roles = roleService.getAllRoles();

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRolesById(@PathVariable("id") int roleId){
        Role role = null;
        try{
            role = roleService.getRoleById(roleId);

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity<Roles> addOrUpdate(@RequestBody Roles role){
//        Roles roles = null;
//        try{
//        roles = roleService.addOrUpdateRole(role);
//
//        }catch(Exception ex){
//        ex.getMessage();
//        }
//        return new ResponseEntity<>(roles,HttpStatus.OK);
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable ("id") int roleId){
        Role role = null;
        try{
            role = roleService.deleteRole(roleId);

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role roleBody, @PathVariable ("id") int roleId){
        Role role = null;
        try{
            role = roleService.updateRole(roleBody, roleId);

        }catch(Exception ex){
            ex.getMessage();
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}