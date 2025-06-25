package cz.jsdh.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller pro zpracování úspěšného odhlášení uživatele.
 */
@Controller
public class LogoutController {

    /**
     * Metoda pro zobrazení stránky po úspěšném odhlášení.
     *
     * @return název šablony "logout.html"
     */
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        // Vrací jméno šablony, která se vykreslí po odhlášení uživatele
        return "logout";
    }
}
