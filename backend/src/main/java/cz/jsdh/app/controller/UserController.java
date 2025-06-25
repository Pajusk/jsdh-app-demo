package cz.jsdh.app.controller;

import cz.jsdh.app.dto.UserDTO;
import cz.jsdh.app.model.Potvrzeni;
import cz.jsdh.app.model.Role;
import cz.jsdh.app.model.User;
import cz.jsdh.app.model.Vyjezd;
import cz.jsdh.app.service.PotvrzeniService;
import cz.jsdh.app.service.UserService;
import cz.jsdh.app.service.VyjezdService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final VyjezdService vyjezdService;
    private final PotvrzeniService potvrzeniService;

    public UserController(UserService userService, VyjezdService vyjezdService, PotvrzeniService potvrzeniService) {
        this.userService = userService;
        this.vyjezdService = vyjezdService;
        this.potvrzeniService = potvrzeniService;
    }

    // Pomocná metoda pro převod User -> UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getFullName(), user.getRole().name());
    }

    /**
     * Zobrazí stránku pro velitele.
     * Velitel vidí aktivní výjezd a všechny členy (hasiče, velitele, adminy) a jejich stav potvrzení.
     */
    @GetMapping("/velitel")
    public String getVelitelUsersPage(Model model) {
        Vyjezd aktivniVyjezd = vyjezdService.findAktivniVyjezd();
        model.addAttribute("aktivniVyjezd", aktivniVyjezd);

        List<Role> roles = Arrays.asList(Role.VELITEL, Role.ADMIN, Role.HASIC);
        List<User> users = userService.findAllByRoleIn(roles);

        List<UserDTO> userDTOs = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        model.addAttribute("users", userDTOs);

        Map<UserDTO, String> stavy = new HashMap<>();
        if (aktivniVyjezd != null) {
            stavy = potvrzeniService.najdiStavyProVyjezdAVelitele(aktivniVyjezd, users);
        }
        model.addAttribute("stavy", stavy);

        return "velitel";
    }

    /**
     * Zobrazí stránku pro hasiče.
     * Hasič vidí aktuální výjezd a stav potvrzení všech hasičů.
     */
    @GetMapping("/hasic")
    public String hasicPage(Model model) {
        Vyjezd aktivniVyjezd = vyjezdService.findAktivniVyjezd();
        model.addAttribute("aktivniVyjezd", aktivniVyjezd);

        List<User> hasici = userService.findAllByRole(Role.HASIC);
        List<Potvrzeni> potvrzeniList = potvrzeniService.findByVyjezd(aktivniVyjezd);

        Map<Long, String> potvrzeniMapa = new HashMap<>();
        for (Potvrzeni p : potvrzeniList) {
            potvrzeniMapa.put(p.getUser().getId(), p.isJede() ? "jede" : "nejede");
        }

        Map<User, String> stavy = new LinkedHashMap<>();
        for (User h : hasici) {
            String stav = potvrzeniMapa.getOrDefault(h.getId(), "nepotvrzeno");
            stavy.put(h, stav);
        }

        model.addAttribute("stavy", stavy);
        return "hasic";
    }

    /**
     * Zpracuje potvrzení nebo zamítnutí účasti hasiče na výjezdu.
     */
    @PostMapping("/hasic/potvrzeni")
    public String zpracujPotvrzeni(
            @RequestParam Long vyjezdId,
            @RequestParam String potvrzeni,
            Authentication authentication
    ) {
        Vyjezd vyjezd = vyjezdService.findById(vyjezdId);
        if (vyjezd == null) {
            return "redirect:/hasic";
        }

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        if (user == null) {
            return "redirect:/login";
        }

        potvrzeniService.ulozNeboAktualizujPotvrzeni(user, vyjezd, potvrzeni);
        return "redirect:/hasic";
    }

    /**
     * Vrací aktuálně přihlášeného uživatele pro použití v šablonách (např. jméno, role).
     */
    @ModelAttribute("loggedUser")
    public User getLoggedUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return userService.findByUsername(authentication.getName());
        }
        return null;
    }
}
