package cz.jsdh.app.controller;

import cz.jsdh.app.model.Vyjezd;
import cz.jsdh.app.service.VyjezdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final VyjezdService vyjezdService;

    /**
     * Konstruktor controlleru s injektovanou službou VyjezdService.
     */
    public AdminController(VyjezdService vyjezdService) {
        this.vyjezdService = vyjezdService;
    }

    /**
     * GET metoda pro zobrazení hlavní administrátorské stránky.
     * Přidá do modelu aktuálně aktivní výjezd.
     *
     * @param model model pro předání dat do šablony
     * @return název šablony "admin"
     */
    @GetMapping
    public String showAdminPage(Model model) {
        Vyjezd aktivni = vyjezdService.findAktivniVyjezd();
        model.addAttribute("aktivniVyjezd", aktivni);
        return "admin";
    }

    /**
     * POST metoda pro aktivaci testovacího výjezdu dle zadaného čísla.
     * Přesměruje zpět na administrátorskou stránku.
     *
     * @param cislo číslo testovacího výjezdu k vložení (např. 1, 2, 3)
     * @return redirect na /admin
     */
    @PostMapping("/aktivovat")
    public String aktivovatVyjezd(@RequestParam int cislo) {
        vyjezdService.vlozTestovaciVyjezd(cislo);
        return "redirect:/admin";
    }
}
