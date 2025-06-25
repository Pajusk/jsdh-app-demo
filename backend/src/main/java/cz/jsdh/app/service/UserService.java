package cz.jsdh.app.service;

import cz.jsdh.app.model.Role;
import cz.jsdh.app.model.User;

import java.util.List;

/**
 * Služba pro práci s uživateli.
 * Definuje základní operace pro správu uživatelů a přístup k jejich údajům.
 */
public interface UserService {

    /**
     * Najde uživatele podle jeho uživatelského jména.
     *
     * @param username uživatelské jméno
     * @return nalezený uživatel nebo null, pokud neexistuje
     */
    User findByUsername(String username);

    /**
     * Uloží nebo aktualizuje uživatele v databázi.
     *
     * @param user uživatel ke uložení
     * @return uložený uživatel
     */
    User save(User user);

    /**
     * Vrátí seznam všech uživatelů.
     *
     * @return seznam všech uživatelů
     */
    List<User> findAll();

    /**
     * Vrátí seznam uživatelů s jednou konkrétní rolí.
     *
     * @param role role uživatele
     * @return seznam uživatelů s danou rolí
     */
    List<User> findAllByRole(Role role);

    /**
     * Vrátí seznam uživatelů, kteří mají některou z daných rolí.
     *
     * @param roles seznam rolí
     * @return seznam uživatelů, kteří mají alespoň jednu z těchto rolí
     */
    List<User> findAllByRoleIn(List<Role> roles);

    /**
     * Vrací aktuální stav potvrzení účasti pro uživatele (např. "jede", "nejede", "nepotvrzeno").
     *
     * @param userId ID uživatele
     * @return textový stav
     */
    String getStavProUzivatele(Long userId);
}
