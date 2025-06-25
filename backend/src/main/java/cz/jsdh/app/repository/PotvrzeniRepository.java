package cz.jsdh.app.repository;

import cz.jsdh.app.model.Potvrzeni;
import cz.jsdh.app.model.User;
import cz.jsdh.app.model.Vyjezd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pro entitu Potvrzeni.
 * Umožňuje CRUD operace a speciální dotazy nad potvrzeními účasti uživatelů na výjezdech.
 */
public interface PotvrzeniRepository extends JpaRepository<Potvrzeni, Long> {

    /**
     * Najde poslední potvrzení podle uživatelského ID, seřazené podle ID sestupně.
     *
     * @param userId ID uživatele
     * @return Optional obsahující nejnovější Potvrzeni pro uživatele, pokud existuje
     */
    Optional<Potvrzeni> findTopByUserIdOrderByIdDesc(Long userId);

    /**
     * Najde všechna potvrzení pro daný výjezd.
     *
     * @param vyjezd Výjezd, ke kterému se potvrzení vztahují
     * @return seznam potvrzení pro daný výjezd
     */
    List<Potvrzeni> findAllByVyjezd(Vyjezd vyjezd);

    /**
     * Najde potvrzení pro daného uživatele a výjezd.
     *
     * @param user uživatel
     * @param vyjezd výjezd
     * @return Optional s potvrzením, pokud existuje
     */
    Optional<Potvrzeni> findByUserAndVyjezd(User user, Vyjezd vyjezd);
}
