package com.oslomet.webprogrammering.uke11;

public class MobelImpl extends Mobel{


    public MobelImpl(String navn, String type, int vekt, int pris, int nummer) {
        super(navn, type, vekt, pris, nummer);
    }

    @Override
    public String toString() {
        return getNummer() + "_" + getNavn();
    }
}
