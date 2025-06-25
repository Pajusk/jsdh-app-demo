package cz.jsdh.app.security;

import cz.jsdh.app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementace UserDetails pro Spring Security, obaluje entitu User.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Gettery pro další použití v aplikaci
    public String getJmeno() {
        return user.getJmeno();
    }

    public String getPrijmeni() {
        return user.getPrijmeni();
    }

    public String getRole() {
        return user.getRole().name();
    }

    public Long getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    /**
     * Vrací roli uživatele ve formátu ROLE_<ROLE_NAME>, jak to Spring Security očekává.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Všechny tyto metody vrací true, protože nemáš implementovaná pravidla pro expiraci nebo zablokování účtu
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
