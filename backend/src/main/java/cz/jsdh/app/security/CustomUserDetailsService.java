package cz.jsdh.app.security;

import cz.jsdh.app.model.User;
import cz.jsdh.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Implementace UserDetailsService pro načítání uživatelů z databáze.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Metoda načte uživatele podle uživatelského jména (username).
     * @param username uživatelské jméno
     * @return UserDetails objekt obsahující informace o uživateli pro Spring Security
     * @throws UsernameNotFoundException pokud uživatel není v DB nalezen
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> Hledám uživatele: " + username);

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Uživatel nenalezen: " + username);
        }

        // Vytvoříme CustomUserDetails, který obaluje entitu User a poskytuje Spring Security požadované metody
        return new CustomUserDetails(user);
    }
}
