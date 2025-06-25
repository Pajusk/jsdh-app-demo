package cz.jsdh.app.model;

/**
 * Výčtový typ reprezentující role uživatelů v systému.
 */
public enum Role {
    ADMIN,     // Administrátor systému – má plný přístup
    VELITEL,   // Velitel jednotky – vidí stav výjezdu, členy a jejich potvrzení
    HASIC;     // Běžný hasič – potvrzuje účast na výjezdu
}