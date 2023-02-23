package com.multiverse.api.springsecuritybackendmultiverseapi.user;

import com.multiverse.api.springsecuritybackendmultiverseapi.recipes.Recipes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    //private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_user",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id"))
    private Set<Recipes> recipes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
