package com.oslomet.webprogrammering.uke11;

public class Stol extends Mobel{

    public Stol(String navn, int vekt, int pris, int nummer) {
        super(navn, "Stol", vekt, pris, nummer);
    }

    @Override
    public String toString() {
        return getNummer() + "_" + getNavn();
    }
}
