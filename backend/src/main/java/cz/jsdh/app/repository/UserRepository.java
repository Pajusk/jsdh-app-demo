package cz.jsdh.app.repository;

import cz.jsdh.app.model.Role;
import cz.jsdh.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pro entitu User.
 * Umožňuje vyhledávání uživatelů podle různých kritérií.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Najde uživatele podle jeho uživatelského jména.
     *
     * @param username uživatelské jméno
     * @return uživatel nebo null, pokud neexistuje
     */
    User findByUsername(String username);

    /**
     * Najde všechny uživatele s konkrétní rolí.
     *
     * @param role role uživatele
     * @return seznam uživatelů s danou rolí
     */
    List<User> findAllByRole(Role role);

    /**
     * Najde všechny uživatele, jejichž role je v zadaném seznamu rolí.
     *
     * @param roles seznam rolí
     * @return seznam uživatelů, kteří mají jednu z těchto rolí
     */
    List<User> findAllByRoleIn(List<Role> roles);
}
