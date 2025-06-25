package cz.jsdh.app.model;

import jakarta.persistence.*;

/**
 * Entita reprezentující výjezd (akci) hasičské jednotky.
 */
@Entity
public class Vyjezd {

    /**
     * Primární klíč výjezdu, automaticky generovaný.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Typ výjezdu, např. "TECHNICKÁ POMOC - ČERPÁNÍ VODY".
     */
    private String typVyjezdu;

    /**
     * Ulice s číslem popisným, kde se výjezd odehrává.
     */
    private String ulice;

    /**
     * Část obce, například "Třebovice".
     */
    private String castObce;

    /**
     * Obec, například "Ostrava".
     */
    private String obec;

    /**
     * Kraj, například "Moravskoslezský".
     */
    private String kraj;

    /**
     * Popis techniky Heřmanice nasazené na výjezd, např. "DA 15 MB Sprinter - HOS 718".
     */
    private String technikaHermanice;

    /**
     * GPS souřadnice místa výjezdu jako text, například "49.830029 N, 18.210924 E".
     */
    private String gps;

    /**
     * Datum a čas výjezdu jako text. V budoucnu lze změnit na LocalDateTime.
     */
    private String datumCas;

    /**
     * Indikátor, zda je výjezd aktivní.
     */
    private boolean aktivni;

    // --- Gettery a settery ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypVyjezdu() {
        return typVyjezdu;
    }

    public void setTypVyjezdu(String typVyjezdu) {
        this.typVyjezdu = typVyjezdu;
    }

    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public String getCastObce() {
        return castObce;
    }

    public void setCastObce(String castObce) {
        this.castObce = castObce;
    }

    public String getObec() {
        return obec;
    }

    public void setObec(String obec) {
        this.obec = obec;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getTechnikaHermanice() {
        return technikaHermanice;
    }

    public void setTechnikaHermanice(String technikaHermanice) {
        this.technikaHermanice = technikaHermanice;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getDatumCas() {
        return datumCas;
    }

    public void setDatumCas(String datumCas) {
        this.datumCas = datumCas;
    }

    public boolean isAktivni() {
        return aktivni;
    }

    public void setAktivni(boolean aktivni) {
        this.aktivni = aktivni;
    }
}
