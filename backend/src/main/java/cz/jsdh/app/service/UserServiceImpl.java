package cz.jsdh.app.service;

import cz.jsdh.app.model.Role;
import cz.jsdh.app.model.User;
import cz.jsdh.app.model.Potvrzeni;
import cz.jsdh.app.repository.PotvrzeniRepository;
import cz.jsdh.app.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementace uživatelské služby.
 * Obsahuje metody pro práci s entitou User a souvisejícím potvrzením.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PotvrzeniRepository potvrzeniRepository;

    public UserServiceImpl(UserRepository userRepository, PotvrzeniRepository potvrzeniRepository) {
        this.userRepository = userRepository;
        this.potvrzeniRepository = potvrzeniRepository;
    }

    /**
     * Najde uživatele podle uživatelského jména.
     *
     * @param username uživatelské jméno
     * @return nalezený uživatel nebo null
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Uloží nebo aktualizuje uživatele.
     *
     * @param user uživatel
     * @return uložený uživatel
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Vrací všechny uživatele v databázi.
     *
     * @return seznam všech uživatelů
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Vrací seznam uživatelů podle konkrétní role.
     *
     * @param role role uživatele
     * @return seznam uživatelů s danou rolí
     */
    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    /**
     * Vrací seznam uživatelů podle více rolí.
     *
     * @param roles seznam rolí
     * @return seznam uživatelů
     */
    @Override
    public List<User> findAllByRoleIn(List<Role> roles) {
        return userRepository.findAllByRoleIn(roles);
    }

    /**
     * Vrací aktuální stav potvrzení pro uživatele (jede / nejede / nepotvrzeno).
     *
     * @param userId ID uživatele
     * @return stav jako text
     */
    @Override
    public String getStavProUzivatele(Long userId) {
        Optional<Potvrzeni> potvrzeniOpt = potvrzeniRepository.findTopByUserIdOrderByIdDesc(userId);
        if (potvrzeniOpt.isPresent()) {
            return potvrzeniOpt.get().isJede() ? "jede" : "nejede";
        }
        return "nepotvrzeno";
    }
}
