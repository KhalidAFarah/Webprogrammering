package com.oslomet.webprogrammering.uke11;

import java.util.Comparator;

public class ComparatorBord implements Comparator<Bord> {

    @Override
    public int compare(Bord b1, Bord b2) {
        return b1.getBen() - b2.getBen();
    }
}
