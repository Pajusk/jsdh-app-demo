package cz.jsdh.app.dto;

/**
 * Data Transfer Object (DTO) pro uživatele.
 * Slouží k přenosu dat o uživateli mezi vrstvami aplikace
 * bez expozice celé entity User.
 */
public class UserDTO {
    private Long id;      // ID uživatele
    private String name;  // Celé jméno uživatele
    private String role;  // Role uživatele jako String (např. "ADMIN", "VELITEL", "HASIC")

    // Prázdný konstruktor pro framework (JPA)
    public UserDTO() {}

    /**
     * Konstruktor pro vytvoření DTO s předanými hodnotami.
     *
     * @param id   ID uživatele
     * @param name Celé jméno uživatele
     * @param role Role uživatele
     */
    public UserDTO(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // Gettery a settery

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
