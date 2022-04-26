package com.oslomet.webprogrammering.eksamener.vaar2021;

public class Pakke {
    private int pid;
    private int kid;
    private int volum;
    private int vekt;

    public Pakke(){}
    public Pakke(int kid, int volum, int vekt) {
        this.kid = kid;
        this.volum = volum;
        this.vekt = vekt;
    }
    public Pakke(int pid, int kid, int volum, int vekt) {
        this.pid = pid;
        this.kid = kid;
        this.volum = volum;
        this.vekt = vekt;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public int getVolum() {
        return volum;
    }

    public void setVolum(int volum) {
        this.volum = volum;
    }

    public int getVekt() {
        return vekt;
    }

    public void setVekt(int vekt) {
        this.vekt = vekt;
    }
}
