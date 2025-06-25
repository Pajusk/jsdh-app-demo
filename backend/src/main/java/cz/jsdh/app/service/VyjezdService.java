package cz.jsdh.app.service;

import cz.jsdh.app.model.Vyjezd;
import cz.jsdh.app.repository.VyjezdRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Služba pro správu výjezdů.
 * Obsahuje logiku pro ukládání, vyhledávání a generování testovacích výjezdů.
 */
@Service
@Transactional
public class VyjezdService {

    private final VyjezdRepository vyjezdRepository;

    public VyjezdService(VyjezdRepository vyjezdRepository) {
        this.vyjezdRepository = vyjezdRepository;
    }

    /**
     * Uloží výjezd do databáze.
     *
     * @param vyjezd výjezd k uložení
     * @return uložený výjezd
     */
    public Vyjezd save(Vyjezd vyjezd) {
        return vyjezdRepository.save(vyjezd);
    }

    /**
     * Vrátí aktuálně aktivní výjezd.
     *
     * @return aktivní výjezd nebo null
     */
    public Vyjezd findAktivniVyjezd() {
        return vyjezdRepository.findByAktivniTrue();
    }

    /**
     * Najde výjezd podle ID.
     *
     * @param id identifikátor výjezdu
     * @return výjezd nebo null
     */
    public Vyjezd findById(Long id) {
        Optional<Vyjezd> optionalVyjezd = vyjezdRepository.findById(id);
        return optionalVyjezd.orElse(null);
    }

    /**
     * Vloží testovací výjezd podle zadaného čísla (1, 2, 3).
     * Nejdříve deaktivuje všechny dosavadní výjezdy.
     *
     * @param cislo číslo testovacího výjezdu
     */
    public void vlozTestovaciVyjezd(int cislo) {
        vyjezdRepository.resetAktivniVyjezdy();  // Zruší předchozí aktivní výjezdy

        Vyjezd vyjezd = new Vyjezd();

        switch (cislo) {
            case 1 -> {
                vyjezd.setTypVyjezdu("TECHNICKÁ POMOC - ČERPÁNÍ VODY");
                vyjezd.setKraj("Moravskoslezský");
                vyjezd.setObec("Ostrava");
                vyjezd.setCastObce("Třebovice");
                vyjezd.setUlice("Elektrárenská 5562/17");
                vyjezd.setGps("49.830029 N, 18.210924 E");
                vyjezd.setTechnikaHermanice("DA 15 MB Sprinter - HOS 997");
                vyjezd.setDatumCas("17.09.2024 09:53");
            }
            case 2 -> {
                vyjezd.setTypVyjezdu("POŽÁR - KOUŘ V OBJEKTU");
                vyjezd.setKraj("Moravskoslezský");
                vyjezd.setObec("Ostrava");
                vyjezd.setCastObce("Poruba");
                vyjezd.setUlice("Hlavní 12");
                vyjezd.setGps("49.820000 N, 18.200000 E");
                vyjezd.setTechnikaHermanice("CAS 30 T815-7 - HOS 998");
                vyjezd.setDatumCas("18.09.2024 14:30");
            }
            case 3 -> {
                vyjezd.setTypVyjezdu("DOPRAVNÍ NEHODA");
                vyjezd.setKraj("Moravskoslezský");
                vyjezd.setObec("Ostrava");
                vyjezd.setCastObce("Svinov");
                vyjezd.setUlice("Nádražní 100");
                vyjezd.setGps("49.840000 N, 18.210000 E");
                vyjezd.setTechnikaHermanice("DA 15 MB Sprinter - HOS 999");
                vyjezd.setDatumCas("19.09.2024 08:15");
            }
            default -> throw new IllegalArgumentException("Neznámé číslo výjezdu: " + cislo);
        }

        vyjezd.setAktivni(true);
        save(vyjezd);
    }
}
