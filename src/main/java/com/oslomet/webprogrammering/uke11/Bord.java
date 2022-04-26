package com.oslomet.webprogrammering.uke11;

public class Bord extends Mobel{

    private int ben;

    public Bord(String navn, int vekt, int pris, int nummer, int ben) {
        super(navn, "Bord", vekt, pris, nummer);

        this.ben = ben;
    }

    @Override
    public String toString() {
        return getNummer() + "_" + getNavn();
    }

    public int getBen() {
        return ben;
    }

    public void setBen(int ben) {
        this.ben = ben;
    }
}
