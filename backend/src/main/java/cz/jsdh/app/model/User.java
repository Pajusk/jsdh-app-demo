package cz.jsdh.app.model;

import jakarta.persistence.*;

/**
 * Entita reprezentující uživatele v systému.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Primární klíč uživatele, generovaný automaticky.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Uživatelské jméno (unikátní).
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Role uživatele (ADMIN, VELITEL, HASIC).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Heslo uživatele (zatím v prostém textu).
     */
    @Column(nullable = false)
    private String password;

    /**
     * Příjmení uživatele.
     */
    @Column(nullable = false)
    private String prijmeni;

    /**
     * Křestní jméno uživatele.
     */
    @Column(nullable = false)
    private String jmeno;

    /**
     * Bezparametrický konstruktor vyžadovaný JPA.
     */
    public User() {
    }

    /**
     * Konstruktor s parametry pro pohodlné vytváření uživatelů.
     *
     * @param username Uživatelské jméno
     * @param password Heslo
     * @param role Role uživatele
     * @param jmeno Křestní jméno
     * @param prijmeni Příjmení
     */
    public User(String username, String password, Role role, String jmeno, String prijmeni) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

    /**
     * Vrací ID uživatele.
     */
    public Long getId() {
        return id;
    }

    /**
     * Vrací uživatelské jméno.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Nastaví uživatelské jméno.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Vrací roli uživatele.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Nastaví roli uživatele.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Vrací heslo uživatele.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Nastaví heslo uživatele.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Vrací příjmení uživatele.
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Nastaví příjmení uživatele.
     */
    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    /**
     * Vrací křestní jméno uživatele.
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Nastaví křestní jméno uživatele.
     */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    /**
     * Vrací celé jméno uživatele (křestní jméno + příjmení).
     */
    public String getFullName() {
        return jmeno + " " + prijmeni;
    }
}
