package com.oslomet.webprogrammering.eksamener.vaar2021;

public class Kunde {
    private int kid;
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String postnr;
    private String telefonnr;
    private String epost;

    public Kunde(){}
    public Kunde(String fornavn, String etternavn, String adresse, String postnr, String telefonnr, String epost) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.postnr = postnr;
        this.telefonnr = telefonnr;
        this.epost = epost;
    }
    public Kunde(int kid, String fornavn, String etternavn, String adresse, String postnr, String telefonnr, String epost) {
        this.kid = kid;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.postnr = postnr;
        this.telefonnr = telefonnr;
        this.epost = epost;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}
