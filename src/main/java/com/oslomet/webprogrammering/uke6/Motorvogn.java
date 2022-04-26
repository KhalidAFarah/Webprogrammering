package com.oslomet.webprogrammering.uke6;

public class Motorvogn {
    private int id;
    private String personnummer;
    private String navn;
    private String adresse;
    private String kjennetegn;
    private String bilmerke;
    private String biltype;

    public Motorvogn () {}
    public Motorvogn (String personnummer, String eiersnavn, String eiersadresse, String kjennetegn, String bilmerke, String biltype){
        this.personnummer = personnummer;
        this.navn = eiersnavn;
        this.adresse = eiersadresse;
        this.kjennetegn = kjennetegn;
        this.bilmerke = bilmerke;
        this.biltype = biltype;
    }
    public Motorvogn (int id, String personnummer, String eiersnavn, String eiersadresse, String kjennetegn, String bilmerke, String biltype){
        this.id = id;
        this.personnummer = personnummer;
        this.navn = eiersnavn;
        this.adresse = eiersadresse;
        this.kjennetegn = kjennetegn;
        this.bilmerke = bilmerke;
        this.biltype = biltype;
    }

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }

    public String getPersonnummer() {
        return personnummer;
    }public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getNavn() {
        return navn;
    }public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getKjennetegn() {
        return kjennetegn;
    }public void setKjennetegn(String kjennetegn) {
        this.kjennetegn = kjennetegn;
    }

    public String getBilmerke() {
        return bilmerke;
    }public void setBilmerke(String bilmerke) {
        this.bilmerke = bilmerke;
    }

    public String getBiltype() {
        return biltype;
    }public void setBiltype(String biltype) {
        this.biltype = biltype;
    }
}
