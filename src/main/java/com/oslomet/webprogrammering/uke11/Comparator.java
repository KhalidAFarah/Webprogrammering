package com.oslomet.webprogrammering.uke11;

public class Comparator implements java.util.Comparator<Mobel> {
    @Override
    public int compare(Mobel m1, Mobel m2) {
        return m1.getNavn().compareTo(m2.getNavn());
    }
}
