package com.oslomet.webprogrammering.uke5;

public class Valuta {
    private String valutaSort;
    private double valutaKurs;

    public Valuta(String valutaSort, double valutaKurs){
        this.valutaSort = valutaSort;
        this.valutaKurs = valutaKurs;
    }

    public String getValutaSort() {
        return valutaSort;
    }

    public void setValutaSort(String valutaSort) {
        this.valutaSort = valutaSort;
    }

    public double getValutaKurs() {
        return valutaKurs;
    }

    public void setValutaKurs(double valutaKurs) {
        this.valutaKurs = valutaKurs;
    }
}
