package cz.jsdh.app.model;

import jakarta.persistence.*;

/**
 * Entita potvrzení účasti uživatele na výjezdu.
 */
@Entity
@Table(name = "potvrzeni")
public class Potvrzeni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Příznak, zda uživatel jede na výjezd (true = jede, false = nejede).
     */
    private boolean jede;

    /**
     * Odkaz na uživatele, který potvrzení vytvořil.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Odkaz na výjezd, kterého se potvrzení týká.
     */
    @ManyToOne
    @JoinColumn(name = "vyjezd_id")
    private Vyjezd vyjezd;

    // === Gettery a settery ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isJede() {
        return jede;
    }

    public void setJede(boolean jede) {
        this.jede = jede;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vyjezd getVyjezd() {
        return vyjezd;
    }

    public void setVyjezd(Vyjezd vyjezd) {
        this.vyjezd = vyjezd;
    }
}
