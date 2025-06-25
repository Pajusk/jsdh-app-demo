package cz.jsdh.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller pro přihlašovací stránku a přesměrování uživatele podle role po přihlášení.
 */
@Controller
public class LoginController {

    /**
     * Zobrazí přihlašovací stránku.
     *
     * @return název šablony "login.html"
     */
    @GetMapping("/login")
    public String login() {
        return "login";  // Thymeleaf šablona login.html v resources/templates/
    }

    /**
     * Přesměruje uživatele na správnou stránku podle jeho role po úspěšném přihlášení.
     *
     * @param authentication obsahuje informace o přihlášeném uživateli
     * @return přesměrování na příslušnou URL podle role nebo zpět na login s chybou
     */
    @GetMapping("/dashboard")
    public String dashboardRedirect(Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String role = authority.getAuthority();

                switch (role) {
                    case "ROLE_ADMIN":
                        return "redirect:/admin";
                    case "ROLE_VELITEL":
                        return "redirect:/velitel";
                    case "ROLE_HASIC":
                        return "redirect:/hasic";
                }
            }
        }
        // Pokud uživatel nemá žádnou z očekávaných rolí, přesměruj na login s parametrem error
        return "redirect:/login?error";
    }

}
