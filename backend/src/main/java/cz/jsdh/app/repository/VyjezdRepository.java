package cz.jsdh.app.repository;

import cz.jsdh.app.model.Vyjezd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VyjezdRepository extends JpaRepository<Vyjezd, Long> {

    /**
     * Najde aktuálně aktivní výjezd (který má nastaveno aktivni = true).
     *
     * @return aktivní výjezd nebo null, pokud žádný není aktivní
     */
    Vyjezd findByAktivniTrue();

    /**
     * Nastaví všechny výjezdy na neaktivní (aktivni = false).
     * Používá se před aktivací nového výjezdu.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Vyjezd v SET v.aktivni = false WHERE v.aktivni = true")
    void resetAktivniVyjezdy();
}
