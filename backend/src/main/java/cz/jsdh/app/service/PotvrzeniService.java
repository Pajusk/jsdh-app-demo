package cz.jsdh.app.service;

import cz.jsdh.app.dto.UserDTO;
import cz.jsdh.app.model.Potvrzeni;
import cz.jsdh.app.model.User;
import cz.jsdh.app.model.Vyjezd;
import cz.jsdh.app.repository.PotvrzeniRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Služba pro práci s potvrzeními účasti na výjezdech.
 */
@Service
public class PotvrzeniService {

    private final PotvrzeniRepository potvrzeniRepository;

    public PotvrzeniService(PotvrzeniRepository potvrzeniRepository) {
        this.potvrzeniRepository = potvrzeniRepository;
    }

    /**
     * Vrátí všechna potvrzení pro daný výjezd.
     *
     * @param vyjezd výjezd, pro který se potvrzení hledají
     * @return seznam potvrzení
     */
    public List<Potvrzeni> findByVyjezd(Vyjezd vyjezd) {
        return potvrzeniRepository.findAllByVyjezd(vyjezd);
    }

    /**
     * Uloží nové potvrzení nebo aktualizuje existující.
     *
     * @param potvrzeni entita potvrzení k uložení
     */
    public void save(Potvrzeni potvrzeni) {
        potvrzeniRepository.save(potvrzeni);
    }

    /**
     * Uloží nové potvrzení nebo aktualizuje existující pro konkrétního uživatele a výjezd.
     * Pokud potvrzení již existuje, přepíše stav.
     *
     * @param user      uživatel, který potvrzuje
     * @param vyjezd    výjezd, kterého se potvrzení týká
     * @param stavText  textový stav ("jede", "nejede")
     */
    public void ulozNeboAktualizujPotvrzeni(User user, Vyjezd vyjezd, String stavText) {
        Optional<Potvrzeni> optionalPotvrzeni = potvrzeniRepository.findByUserAndVyjezd(user, vyjezd);

        Potvrzeni potvrzeni = optionalPotvrzeni.orElse(new Potvrzeni());
        potvrzeni.setUser(user);
        potvrzeni.setVyjezd(vyjezd);
        potvrzeni.setJede("jede".equalsIgnoreCase(stavText));

        potvrzeniRepository.save(potvrzeni);
    }

    /**
     * Vrátí mapu potvrzených stavů pro daný výjezd a seznam uživatelů.
     * Každému uživateli přiřadí stav: "jede", "nejede", nebo "nepotvrzeno".
     *
     * @param vyjezd       výjezd, pro který se stavy zjišťují
     * @param uzivatele    seznam uživatelů (např. velitelé)
     * @return mapa UserDTO -> stav jako text
     */
    public Map<UserDTO, String> najdiStavyProVyjezdAVelitele(Vyjezd vyjezd, List<User> uzivatele) {
        List<Potvrzeni> potvrzeniList = potvrzeniRepository.findAllByVyjezd(vyjezd);

        // Mapování podle ID uživatele pro rychlé vyhledání
        Map<Long, Potvrzeni> potvrzeniMap = potvrzeniList.stream()
                .collect(Collectors.toMap(
                        p -> p.getUser().getId(),
                        p -> p,
                        (p1, p2) -> p1 // řešení duplicit: necháme první
                ));

        Map<UserDTO, String> stavy = new LinkedHashMap<>();
        for (User uzivatel : uzivatele) {
            Potvrzeni p = potvrzeniMap.get(uzivatel.getId());
            String stav = (p == null) ? "nepotvrzeno" : (p.isJede() ? "jede" : "nejede");

            UserDTO dto = new UserDTO(
                    uzivatel.getId(),
                    uzivatel.getFullName(),
                    uzivatel.getRole().name()
            );
            stavy.put(dto, stav);
        }

        return stavy;
    }
}
